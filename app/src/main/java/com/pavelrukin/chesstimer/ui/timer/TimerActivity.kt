package com.pavelrukin.chesstimer.ui.timer

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import com.pavelrukin.chesstimer.R
import com.pavelrukin.chesstimer.ui.settings.SettingsActivity
import kotlinx.android.synthetic.main.activity_timer.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class TimerActivity : MvpAppCompatActivity(), TimerView,
    SharedPreferences.OnSharedPreferenceChangeListener {
    override fun onSharedPreferenceChanged(p0: SharedPreferences?, p1: String?) {
        if (p0 != null && p1 != null) {
            timerPresenter.changeSettings(this, p0, p1)
        }
    }

    override fun setSettings(time: String) {

        tv_counter_player_first.text = time
        tv_counter_player_second.text = time
    }

    override fun setTimerFirst(time: String) {
        tv_counter_player_first.text = time
        cv_first_player.setCardBackgroundColor(Color.GREEN)
        cv_second_player.setCardBackgroundColor(Color.WHITE)
    }

    override fun setTimerSecond(time: String) {
        tv_counter_player_second.text = time
        cv_second_player.setCardBackgroundColor(Color.GREEN)
        cv_first_player.setCardBackgroundColor(Color.WHITE)
    }

    override fun setFinishFirst(time: String) {
        tv_counter_player_first.text = time
        cv_first_player.setCardBackgroundColor(Color.BLUE)
        cv_first_player.isEnabled = false
    }

    override fun setFinishSecond(time: String) {
        tv_counter_player_second.text = time
        cv_second_player.setCardBackgroundColor(Color.BLUE)
        cv_second_player.isEnabled = false
    }

    @InjectPresenter
    lateinit var timerPresenter: TimerPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)
        setupViews()

        timerPresenter.setSettings(this)
    }

    private fun setupViews() {
        cv_first_player.setOnClickListener {
            timerPresenter.startSecondTimer()
            timerPresenter.stopFirstTimer()
            cv_first_player.isEnabled = false
            cv_second_player.isEnabled = true
        }
        cv_second_player.setOnClickListener {
            timerPresenter.startFirstTimer()
            timerPresenter.stopSecondTimer()
            cv_second_player.isEnabled = false
            cv_first_player.isEnabled = true
        }
        fab_refresh.setOnClickListener { p0 ->
            timerPresenter.stopFirstTimer()
            timerPresenter.stopSecondTimer()
            timerPresenter.setSettings(p0!!.context)

            cv_first_player.setCardBackgroundColor(Color.WHITE)
            cv_second_player.setCardBackgroundColor(Color.WHITE)
            cv_first_player.isEnabled = true
            cv_second_player.isEnabled = true
        }

        fab_settings.setOnClickListener {
            startActivity(Intent(applicationContext, SettingsActivity::class.java))
        }

        fab_pause.setOnClickListener {
            timerPresenter.stopFirstTimer()
            timerPresenter.stopSecondTimer()
        }
    }


}





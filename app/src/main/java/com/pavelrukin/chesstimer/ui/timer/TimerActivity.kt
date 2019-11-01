package com.pavelrukin.chesstimer.ui.timer

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.WindowManager
import com.pavelrukin.chesstimer.R
import com.pavelrukin.chesstimer.ui.settings.SettingsActivity
import kotlinx.android.synthetic.main.activity_timer.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import timber.log.Timber


class TimerActivity : MvpAppCompatActivity(), ITimerView,
    SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onSharedPreferenceChanged(p0: SharedPreferences?, p1: String?) {
        if (p0 != null && p1 != null) {
            timerPresenter.changeSettings(this, p0, p1)
        }
    }


    override fun setVibration() {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        Timber.i("setVibration() call")
        if (vibrator.hasVibrator()) { // Vibrator availability checking
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE)) // New vibrate method for API Level 26 or higher
            } else {
                vibrator.vibrate(500) // Vibrate method for below API Level 26
            }
        }
    }

      /*  val vibration = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibration.vibrate(1000)
        }*/


    override fun setSound() {
        val playMP = MediaPlayer.create(this,R.raw.click_sound)
        playMP.start()
    }



    override fun setSettings(
        time: String,
        vibration: Boolean,
        soundClick: Boolean
    ) {
        tv_counter_player_first.text = time
        tv_counter_player_second.text = time
    }

    override fun setTimerFirst(time: String) {
        tv_counter_player_first.text = time
        cv_first_player.setCardBackgroundColor(resources.getColor(R.color.colorAction))
        cv_second_player.setCardBackgroundColor(resources.getColor(R.color.colorActionPause))
    }

    override fun setTimerSecond(time: String) {
        tv_counter_player_second.text = time
        cv_second_player.setCardBackgroundColor(resources.getColor(R.color.colorAction))
        cv_first_player.setCardBackgroundColor(resources.getColor(R.color.colorActionPause))
    }

    override fun setFinishFirst(time: String) {
        tv_counter_player_first.text = time
        cv_first_player.setCardBackgroundColor(resources.getColor(R.color.colorAccent))
        cv_first_player.isEnabled = false
    }

    override fun setFinishSecond(time: String) {
        tv_counter_player_second.text = time
        cv_second_player.setCardBackgroundColor(resources.getColor(R.color.colorAccent))
        cv_second_player.isEnabled = false
    }

    @InjectPresenter
    lateinit var timerPresenter: TimerPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)
        setupViews()
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        timerPresenter.setSettings(this)
    }

    private fun setupViews() {
        cv_first_player.setOnClickListener {
timerPresenter.setSoundCall()
            timerPresenter.setVibrateCall()
            timerPresenter.startSecondTimer()
            timerPresenter.stopFirstTimer()
            cv_first_player.isEnabled = false
            cv_second_player.isEnabled = true
        }
        cv_second_player.setOnClickListener {
            timerPresenter.setSoundCall()
timerPresenter.setVibrateCall()
            timerPresenter.startFirstTimer()
            timerPresenter.stopSecondTimer()
            cv_second_player.isEnabled = false
            cv_first_player.isEnabled = true
        }
        fab_refresh.setOnClickListener { p0 ->
            timerPresenter.stopFirstTimer()
            timerPresenter.stopSecondTimer()
            timerPresenter.setSettings(p0!!.context)

            cv_first_player.setCardBackgroundColor(resources.getColor(R.color.colorActionPause))
            cv_second_player.setCardBackgroundColor(resources.getColor(R.color.colorActionPause))
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





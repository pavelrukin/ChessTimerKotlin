package com.pavelrukin.chesstimer.ui.timer

import android.graphics.Color
import android.os.Bundle

import com.pavelrukin.chesstimer.R
import kotlinx.android.synthetic.main.activity_timer.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class TimerActivity : MvpAppCompatActivity(), TimerView {


    override fun setTimerFirst(time: String) {
        tv_counter_player_first.text = time
    }

    override fun setTimerSecond(time: String) {
        tv_counter_player_second.text = time
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

        cv_first_player.setOnClickListener {
            timerPresenter.startSecondTimer()
            timerPresenter.stopFirstTimer()
        }
        cv_second_player.setOnClickListener {
            timerPresenter.startFirstTimer()
            timerPresenter.stopSecondTimer()
        }
    }

    fun initView() {

    }

}

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


        //TODO create FragmentSettings


        fab_pause.setOnClickListener {
            timerPresenter.stopFirstTimer()
            timerPresenter.stopSecondTimer()
        }
//Хуево WORKING VERSION
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

//НЕ ТАК ХУЕВО РАБОТАЕТ, НО ЕСЛИ НЕ ПЕРЕВАРАЧИВАТЬ
        /*cv_first_player.setOnClickListener {
            timerPresenter.startSecondTimer()
            timerPresenter.stopFirstTimer()

            cv_first_player.isEnabled = false

            cv_second_player.setCardBackgroundColor(Color.GREEN)
            cv_first_player.setCardBackgroundColor(Color.WHITE)

            cv_second_player.isEnabled = true
        }
        cv_second_player.setOnClickListener {

            timerPresenter.startFirstTimer()
            timerPresenter.stopSecondTimer()

            cv_second_player.isEnabled = false
            cv_first_player.setCardBackgroundColor(Color.GREEN)
            cv_second_player.setCardBackgroundColor(Color.WHITE)

            cv_first_player.isEnabled = true
        }*/
    }
}

fun initView() {

}



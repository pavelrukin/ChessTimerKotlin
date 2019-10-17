package com.pavelrukin.chesstimer.ui.timer

import android.os.CountDownTimer

import com.pavelrukin.chesstimer.App
import com.pavelrukin.chesstimer.constants.Constants.Companion.DEFAULT_TIME
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class TimerPresenter : MvpPresenter<TimerView>() {

    @Inject
    lateinit var timerModel: TimerModel

    private var timerFirst: CountDownTimer? = null
    private var timerSecond: CountDownTimer? = null

    var seconds1: Long = DEFAULT_TIME
    var seconds2: Long = DEFAULT_TIME
init {
    App.appComponent.inject(this)
}

    fun startFirstTimer(interval: Long = 1 * 1000) {
        timerFirst = object : CountDownTimer(seconds1 * 1000, interval) {
            override fun onTick(count: Long) {
                seconds1 = count / 1000

                viewState.setTimerFirst(timerModel.timeFormat(seconds1))

            }

            override fun onFinish() {
                viewState.setFinishFirst(timerModel.timeFormat(0))
            }
        }.start()
    }

    fun stopFirstTimer() {
        if (timerFirst != null) {
            timerFirst?.cancel()
        }
    }

    fun startSecondTimer(interval: Long = 1 * 1000) {
        timerSecond = object : CountDownTimer(seconds2 * 1000, interval) {

            override fun onTick(count: Long) {

                seconds2 = count / 1000
                viewState.setTimerSecond(timerModel.timeFormat(seconds2))
            }

            override fun onFinish() {
                viewState.setFinishSecond(timerModel.timeFormat(0))
            }
        }.start()
    }

    fun stopSecondTimer() {
        if (timerSecond != null) {
            timerSecond?.cancel()
        }
    }
}
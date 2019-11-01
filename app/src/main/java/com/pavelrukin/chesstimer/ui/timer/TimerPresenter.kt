package com.pavelrukin.chesstimer.ui.timer

import android.content.Context
import android.content.SharedPreferences
import android.os.CountDownTimer
import com.pavelrukin.chesstimer.App
import com.pavelrukin.chesstimer.R
import com.pavelrukin.chesstimer.constants.Constants.Companion.DEFAULT_TIME
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class TimerPresenter : MvpPresenter<ITimerView>() {

    @Inject
    lateinit var timerModel: TimerModel

    private var timerFirst: CountDownTimer? = null
    private var timerSecond: CountDownTimer? = null

    var timeForFirst: Long = DEFAULT_TIME
    var timeForSecond: Long = DEFAULT_TIME

    var vibration: Boolean = true

    init {
        App.appComponent.inject(this)
    }

    fun setVibrateCall() {
        if (vibration) {
            viewState.setVibration()
        }
    }

    fun setSettings(context: Context) {
        val soundClick = timerModel.getSoundClickPreference(context)
        val vibration = timerModel.getVibrationPreference(context)
        val time = timerModel.getTimePreference(context)
        timeForFirst = time
        timeForSecond = time

        this.vibration = vibration

        viewState.setSettings(timerModel.timeFormat(time), vibration, soundClick)
    }

    fun changeSettings(context: Context, preferences: SharedPreferences, key: String) {
        when (key) {
            context.getString(R.string.key_vibration) -> {
                vibration = preferences.getBoolean(key, true)
            }
            context.getString(R.string.key_timer_length) -> {
                timeForFirst = preferences.getInt(key, DEFAULT_TIME.toInt() / 60).toLong() * 60
                timeForSecond = preferences.getInt(key, DEFAULT_TIME.toInt() / 60).toLong() * 60
            }
        }
        viewState.setSettings(timerModel.timeFormat(timeForFirst), vibration, soundClick = true)
    }

    fun startFirstTimer(interval: Long = 1 * 1000) {
        timerFirst = object : CountDownTimer(timeForFirst * 1000, interval) {
            override fun onTick(count: Long) {
                timeForFirst = count / 1000
                viewState.setTimerFirst(timerModel.timeFormat(timeForFirst))
                if (vibration) {
                    if (timeForFirst.toInt() == 1 * 60) {
                        viewState.setVibration()
                    }
                }
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
        timerSecond = object : CountDownTimer(timeForSecond * 1000, interval) {
            override fun onTick(count: Long) {
                timeForSecond = count / 1000
                viewState.setTimerSecond(timerModel.timeFormat(timeForSecond))
                if (vibration) {
                    if (timeForSecond.toInt() == 1 * 60) {
                        viewState.setVibration()
                    }
                }


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
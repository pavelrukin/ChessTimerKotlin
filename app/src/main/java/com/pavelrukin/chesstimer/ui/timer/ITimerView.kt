package com.pavelrukin.chesstimer.ui.timer

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ITimerView :MvpView {

    fun setTimerFirst(time: String)

    fun setTimerSecond(time: String)

    fun setFinishFirst(time: String)

    fun setFinishSecond(time: String)

    fun setSettings(time: String, vibration: Boolean, soundClick: Boolean)

    fun setVibration()

    fun setSound()


}

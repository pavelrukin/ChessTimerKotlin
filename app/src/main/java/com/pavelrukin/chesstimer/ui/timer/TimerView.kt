package com.pavelrukin.chesstimer.ui.timer

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface TimerView :MvpView {

    fun setTimerFirst(time: String)

    fun setTimerSecond(time: String)

    fun setFinishFirst(time: String)

    fun setFinishSecond(time: String)
}

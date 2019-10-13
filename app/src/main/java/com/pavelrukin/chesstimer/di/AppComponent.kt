package com.pavelrukin.chesstimer.di

import com.pavelrukin.chesstimer.ui.timer.TimerPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TimerModule::class])
interface AppComponent {
    fun inject(timerPresenter: TimerPresenter)
}
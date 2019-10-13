package com.pavelrukin.chesstimer.di

import com.pavelrukin.chesstimer.ui.timer.TimerModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class TimerModule {
    @Provides
    @Singleton
    fun timerModel(): TimerModel = TimerModel()




}
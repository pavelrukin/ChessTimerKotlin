package com.pavelrukin.chesstimer.ui.timer

import java.util.concurrent.TimeUnit

class TimerModel{

    fun timeFormat(seconds: Long): String {
        return String.format("%02d:%02d",
            TimeUnit.SECONDS.toMinutes(seconds),
            TimeUnit.SECONDS.toSeconds(seconds) - TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(seconds)))
    }

}
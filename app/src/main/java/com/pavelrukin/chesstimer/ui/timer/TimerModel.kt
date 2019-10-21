package com.pavelrukin.chesstimer.ui.timer

import android.content.Context
import android.preference.PreferenceManager
import com.pavelrukin.chesstimer.R
import com.pavelrukin.chesstimer.constants.Constants
import java.util.concurrent.TimeUnit

class TimerModel{

    fun timeFormat(seconds: Long): String {
        return String.format("%02d:%02d",
            TimeUnit.SECONDS.toMinutes(seconds),
            TimeUnit.SECONDS.toSeconds(seconds) - TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(seconds)))
    }
    fun getTimePreference(context: Context): Long {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getInt(context.getString(R.string.key_timer_length), Constants.DEFAULT_TIME.toInt() / 60).toLong() * 60
    }

}
package com.pavelrukin.chesstimer.ui.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.pavelrukin.chesstimer.R
import com.pavelrukin.chesstimer.constants.Constants
import kotlinx.android.synthetic.main.activity_settings.*


class SettingsActivityFragment : PreferenceFragmentCompat(){


    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)

        val screen = preferenceScreen.sharedPreferences
        val time = findPreference<Preference>(getString(R.string.key_timer_length))
     /*   if (time != null) {
            time.summary =screen.getInt(getString(R.string.key_timer_length), Constants.DEFAULT_TIME.toInt() / 60).toString() + " минут"
        }

        time!!.onPreferenceChangeListener = Preference.OnPreferenceChangeListener { p0, p1 ->
            time.summary = p1.toString() + " минут"
            true

*/

        }
    }






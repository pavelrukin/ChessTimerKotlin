package com.pavelrukin.chesstimer.ui.settings

import android.os.Bundle

import androidx.preference.PreferenceFragmentCompat
import com.pavelrukin.chesstimer.R


class SettingsActivityFragment : PreferenceFragmentCompat() {


    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)

    }





}
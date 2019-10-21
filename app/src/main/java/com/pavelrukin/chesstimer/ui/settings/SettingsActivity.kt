package com.pavelrukin.chesstimer.ui.settings


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pavelrukin.chesstimer.R
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_settings)
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = getString(R.string.action_settings)
        }
}

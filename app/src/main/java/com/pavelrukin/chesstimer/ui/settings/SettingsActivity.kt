package com.pavelrukin.chesstimer.ui.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.pavelrukin.chesstimer.R
import kotlinx.android.synthetic.main.activity_settings.*
import timber.log.Timber


class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("onCreate() called")
        setContentView(R.layout.activity_settings)
        setupFragment()
        setupToolbar()
    }

    fun setupFragment() {
        val settingsFragment: android.app.Fragment? =
            fragmentManager.findFragmentById(R.id.fragment)
        if (settingsFragment != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, SettingsActivityFragment())
                .commit()
        }
    }
    fun setupToolbar() {

        setSupportActionBar(toolbar as Toolbar?)
        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (toolbar as Toolbar?)?.setNavigationOnClickListener {
            this.onBackPressed()
        }
    }
}


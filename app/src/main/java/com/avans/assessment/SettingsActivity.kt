package com.avans.assessment

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import androidx.preference.EditTextPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.avans.assessment.services.SettingsService

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }

        supportActionBar?.title = "Settings"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    class SettingsFragment : PreferenceFragmentCompat() {

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)

            val preference = preferenceScreen.findPreference<EditTextPreference>("minimum_alcohol");

            preference?.onPreferenceChangeListener = Preference.OnPreferenceChangeListener { pref, newValue ->
                val value: String = newValue.toString()

                if (value.isDigitsOnly() && value.isNotEmpty()){
                    return@OnPreferenceChangeListener true
                }

                Toast.makeText(context,"Validation Failed", Toast.LENGTH_SHORT).show()

                false
            }
        }
    }
}
package dev.hyuwah.dicoding.muvilog.presentation.settings

import android.os.Bundle
import android.widget.Toast
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import dev.hyuwah.dicoding.muvilog.R
import dev.hyuwah.dicoding.muvilog.presentation.base.BaseActivity
import dev.hyuwah.dicoding.muvilog.setLocale
import java.util.*

class SettingsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        title = resources.getString(R.string.title_activity_settings)
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.settings,
                SettingsFragment()
            )
            .commit()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
            findPreference<ListPreference>("setting_lang")?.setOnPreferenceChangeListener { preference, newValue ->
                when (newValue) {
                    "en" -> {
                        Toast.makeText(requireActivity(), "Changed to en", Toast.LENGTH_SHORT)
                            .show()
                    }
                    "id" -> {
                        Toast.makeText(requireActivity(), "Changed to in", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                requireActivity().setLocale(Locale(newValue.toString()))
                requireActivity().recreate()
                true
            }

            findPreference<SwitchPreferenceCompat>("setting_daily_reminder")
        }
    }
}
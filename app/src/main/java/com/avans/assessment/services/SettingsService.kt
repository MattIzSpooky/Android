package com.avans.assessment.services

import android.content.Context
import androidx.preference.PreferenceManager
import java.lang.ref.WeakReference

class SettingsService(ctx: Context): BaseService(ctx) {
    fun getPreferenceByKey(key: String): String {
        val pref = PreferenceManager.getDefaultSharedPreferences(retrieveContextOrThrow())
        return pref?.getString(key, "").toString()
    }
}
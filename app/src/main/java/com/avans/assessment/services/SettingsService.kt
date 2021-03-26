package com.avans.assessment.services

import android.content.Context
import java.lang.ref.WeakReference

class SettingsService(ctx: Context) {
    private val context = WeakReference(ctx)
    private val Key: String = "minimum_alcohol"

    fun save(value: String) {
        val pref = context.get()?.getSharedPreferences("settings", Context.MODE_PRIVATE)

        with(pref?.edit()){
            this?.putString(Key, value)
            this?.apply()
        }
    }

    fun get(): String {
        val pref = context.get()?.getSharedPreferences("settings", Context.MODE_PRIVATE)

        return pref?.getString(Key, "").toString()
    }
}
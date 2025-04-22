package com.dov4k1n.tatarapp.data.local

import android.content.Context
import androidx.core.content.edit

sealed class PrefKey(val key: String) {
    object ThemeMode : PrefKey("theme_mode")
}

sealed class ThemeMode(val value: String) {
    object Light : ThemeMode("light")
    object Dark : ThemeMode("dark")
    object System : ThemeMode("system")

    companion object {
        fun fromString(value: String): ThemeMode = when (value) {
            "light" -> Light
            "dark" -> Dark
            else -> System
        }
    }
}

fun ThemeMode.next(): ThemeMode {
    return when (this) {
        ThemeMode.Light -> ThemeMode.Dark
        ThemeMode.Dark -> ThemeMode.System
        ThemeMode.System -> ThemeMode.Light
    }
}

class PreferencesManager(context: Context) {
    private val sharedPreferences = context.getSharedPreferences(
        "app_preferences",
        Context.MODE_PRIVATE
    )

    fun saveThemeMode(themeMode: ThemeMode) {
        sharedPreferences.edit {
            putString(PrefKey.ThemeMode.key, themeMode.value)
        }
    }

    fun getThemeMode(): ThemeMode {
        val savedValue = sharedPreferences.getString(
            PrefKey.ThemeMode.key,
            ThemeMode.System.value
        )
        return ThemeMode.fromString(savedValue ?: ThemeMode.System.value)
    }

    fun saveString(key: String, value: String) {
        sharedPreferences.edit {
            putString(key, value)
        }
    }

    fun getString(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    fun saveBoolean(key: String, value: Boolean) {
        sharedPreferences.edit {
            putBoolean(key, value)
        }
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    fun clear() {
        sharedPreferences.edit {
            clear()
        }
    }
}
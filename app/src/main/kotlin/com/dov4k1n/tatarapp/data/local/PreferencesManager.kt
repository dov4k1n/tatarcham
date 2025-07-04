/*
 * Tatarcham v0.2, tatar language learning app for Android.
 * Copyright (C) 2023-2025 Ayzat Rizatdinov <ddov4k1n at gmail dot com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see https://www.gnu.org/licenses/.
 *
 */

package com.dov4k1n.tatarapp.data.local

import android.content.Context
import androidx.core.content.edit
import com.dov4k1n.tatarapp.data.BottomAppBarItem

class PreferencesManager(context: Context) {
    private val appPreferences = context.getSharedPreferences(
        "app_preferences",
        Context.MODE_PRIVATE
    )

    fun saveThemeMode(themeMode: ThemeMode) {
        appPreferences.edit {
            putString(PrefKey.ThemeMode.key, themeMode.value)
        }
    }

    fun getThemeMode(): ThemeMode {
        val savedValue = appPreferences.getString(
            PrefKey.ThemeMode.key,
            ThemeMode.System.value
        )
        return ThemeMode.fromString(savedValue ?: ThemeMode.System.value)
    }

    fun saveLastTabOpen(tab: BottomAppBarItem) {
        appPreferences.edit {
            putString(PrefKey.LastTabOpen.key, tab.route)
        }
    }

    fun getLastTabOpen(): BottomAppBarItem {
        val savedValue = appPreferences.getString(
            PrefKey.LastTabOpen.key,
            BottomAppBarItem.Phonetics.route
        )
        return BottomAppBarItem
            .fromString(savedValue ?: BottomAppBarItem.Phonetics.route)
    }

    fun saveString(key: String, value: String) {
        appPreferences.edit {
            putString(key, value)
        }
    }

    fun getString(key: String, defaultValue: String): String {
        return appPreferences.getString(key, defaultValue) ?: defaultValue
    }

    fun saveBoolean(key: String, value: Boolean) {
        appPreferences.edit {
            putBoolean(key, value)
        }
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return appPreferences.getBoolean(key, defaultValue)
    }

    fun clear() {
        appPreferences.edit {
            clear()
        }
    }
}

sealed class PrefKey(val key: String) {
    object ThemeMode : PrefKey("theme_mode")
    object LastTabOpen : PrefKey("last_tab_open")
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

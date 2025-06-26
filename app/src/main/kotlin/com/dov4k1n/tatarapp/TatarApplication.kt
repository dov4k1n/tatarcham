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

package com.dov4k1n.tatarapp

import android.app.Application
import com.dov4k1n.tatarapp.data.local.PreferencesManager
import com.dov4k1n.tatarapp.data.local.StatsManager

class TatarApplication : Application() {

    lateinit var preferencesManager: PreferencesManager
    lateinit var statsManager: StatsManager

    override fun onCreate() {
        super.onCreate()
        preferencesManager = PreferencesManager(this)
        statsManager = StatsManager(this)

        statsManager.saveDayFirstLaunch()
        statsManager.saveDaysPassedCount()
    }
}
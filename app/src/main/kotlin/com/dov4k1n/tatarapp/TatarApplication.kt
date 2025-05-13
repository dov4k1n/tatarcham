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
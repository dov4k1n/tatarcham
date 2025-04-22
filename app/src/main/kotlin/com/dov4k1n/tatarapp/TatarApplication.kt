package com.dov4k1n.tatarapp

import android.app.Application
import com.dov4k1n.tatarapp.data.local.PreferencesManager

class TatarApplication : Application() {

    lateinit var preferencesManager: PreferencesManager

    override fun onCreate() {
        super.onCreate()
        preferencesManager = PreferencesManager(this)
    }
}
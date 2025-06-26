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

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.Date
import java.util.concurrent.TimeUnit

fun daysBetween(
    date1: String,
    date2: String,
    pattern: String = "EEE, d MMM yyyy HH:mm z"
): Long? {
    val sdf = SimpleDateFormat(pattern, Locale.US)
    try {
        val d1: Date = sdf.parse(date1)!!
        val d2: Date = sdf.parse(date2)!!

        val diffInMillis = kotlin.math.abs(d2.time - d1.time)
        return TimeUnit.MILLISECONDS.toDays(diffInMillis)
    } catch (e: Exception) {
        e.printStackTrace()
        return null
    }
}

fun getCurrentDateFull(): String {
    val calendar = Calendar.getInstance()
    val sdf = SimpleDateFormat("EEE, d MMM yyyy HH:mm z", Locale.US)
    val currentDate = sdf.format(calendar.time)
    return currentDate
}

fun getCurrentDate(): String {
    val calendar = Calendar.getInstance()
    val sdf = SimpleDateFormat("EEE, d MMM yyyy", Locale.US)
    val currentDate = sdf.format(calendar.time)
    return currentDate
}

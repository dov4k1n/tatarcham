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

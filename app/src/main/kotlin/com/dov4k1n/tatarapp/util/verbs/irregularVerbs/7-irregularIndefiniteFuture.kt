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

package com.dov4k1n.tatarapp.util

val irregularVerbsIndefiniteFuture = mapOf(
    "эл" to mapOf(
        "мин" to "эләрмен",
        "син" to "эләрсең",
        "ул" to "эләр",
        "без" to "эләрбез",
        "сез" to "эләрсез",
        "алар" to "эләрләр"
    ),
    "көл" to mapOf(
        "мин" to "көләрмен",
        "син" to "көләрсең",
        "ул" to "көләр",
        "без" to "көләрбез",
        "сез" to "көләрсез",
        "алар" to "көләрләр"
    ),
    "курык" to mapOf(
        "мин" to "куркырмын",
        "син" to "куркырсың",
        "ул" to "куркыр",
        "без" to "куркырбыз",
        "сез" to "куркырсыз",
        "алар" to "куркырлар"
    )
    // ... другие неправильные глаголы
)
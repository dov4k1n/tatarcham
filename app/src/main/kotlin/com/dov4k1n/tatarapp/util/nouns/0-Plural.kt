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

package com.dov4k1n.tatarapp.util.nouns

import com.dov4k1n.tatarapp.util.VowelType
import com.dov4k1n.tatarapp.util.findLastVowelType

fun toPlural(word: String, trash: String): String {
    val lastVowelType = findLastVowelType(word)

    return when (word.last()) {
        'м', 'н', 'ң' -> when (lastVowelType) {
            VowelType.HARD -> word + "нар"
            VowelType.SOFT -> word + "нәр"
        }

        else -> when (lastVowelType) {
            VowelType.HARD -> word + "лар"
            VowelType.SOFT -> word + "ләр"
        }
    }
}
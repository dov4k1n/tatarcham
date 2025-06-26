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

import com.dov4k1n.tatarapp.util.verbs.Verb

class DefinitePast: Verb {
    override fun getConjugationBase(root: String): String {
        val lastVowelType = findLastVowelType(root)

        return when {
            unvoicedConsonants.contains(root.last()) -> {
                when (lastVowelType) {
                    VowelType.HARD -> root + "ты"
                    VowelType.SOFT -> root + "те"
                }
            }
            else -> {
                when (lastVowelType) {
                    VowelType.HARD -> root + "ды"
                    VowelType.SOFT -> root + "де"
                }
            }
        }
    }

    override fun conjugate(root: String, pronoun: String): String {
        val lastVowelType = findLastVowelType(root)
        val suffix = when {
            (pronoun == "мин") -> "м"
            (pronoun == "син") -> "ң"
            (pronoun == "ул") -> ""
            (pronoun == "без") -> "к"
            (pronoun == "сез") && lastVowelType == VowelType.HARD -> "гыз"
            (pronoun == "сез") && lastVowelType == VowelType.SOFT -> "гез"
            (pronoun == "алар") && lastVowelType == VowelType.HARD -> "лар"
            (pronoun == "алар") && lastVowelType == VowelType.SOFT -> "ләр"
            else -> {
                throw IllegalArgumentException(
                    "Unsupported pronoun and vowel combination: " +
                    "pronoun = $pronoun, lastVowelType = $lastVowelType"
                )
            }
        }
        return smashDiftongs(getConjugationBase(root) + suffix)
    }
}
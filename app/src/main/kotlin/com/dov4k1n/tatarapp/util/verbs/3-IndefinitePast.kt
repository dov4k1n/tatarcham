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

class IndefinitePast: Verb {
    override fun getConjugationBase(root: String): String {
        val lastVowelType = findLastVowelType(root)
        val lastChar = root.last()

        return when {
            unvoicedConsonants.contains(lastChar) ->
                when (lastVowelType) {
                    VowelType.HARD -> root+ "кан"
                    VowelType.SOFT -> root+ "кән"
                }
            else -> {
                when (lastVowelType) {
                    VowelType.HARD -> root + "ган"
                    VowelType.SOFT -> root + "гән"
                }
            }
        }
    }

    override fun conjugate(root: String, pronoun: String): String {
        val lastVowelType = findLastVowelType(root)
        val suffix = when {
            (pronoun == "мин") && lastVowelType == VowelType.HARD -> "мын"
            (pronoun == "мин") && lastVowelType == VowelType.SOFT -> "мен"
            (pronoun == "син") && lastVowelType == VowelType.HARD -> "сың"
            (pronoun == "син") && lastVowelType == VowelType.SOFT -> "сең"
            (pronoun == "ул") -> ""
            (pronoun == "без") && lastVowelType == VowelType.HARD -> "быз"
            (pronoun == "без") && lastVowelType == VowelType.SOFT -> "без"
            (pronoun == "сез") && lastVowelType == VowelType.HARD -> "сыз"
            (pronoun == "сез") && lastVowelType == VowelType.SOFT -> "сез"
            (pronoun == "алар") && lastVowelType == VowelType.HARD -> "нар"
            (pronoun == "алар") && lastVowelType == VowelType.SOFT -> "нәр"
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
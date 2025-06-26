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

class Present: Verb {
    override fun getConjugationBase(root: String): String {
        val lastVowelType = findLastVowelType(root)
        val lastChar = root.last()

        when (lastChar) {
            'ү' -> return root + "ә"
            'у','ю' -> return root + "а"
            'и' -> return root + "я"

            in softVowels -> return root.dropLast(1) + "и"

            in hardVowels -> return root.dropLast(1) + "ый"

            in mutableVowels -> return when (lastVowelType) {
                    VowelType.HARD -> root.dropLast(1) + "ый"
                    VowelType.SOFT -> root.dropLast(1) + "и"
            }

            else -> return when (lastVowelType) {
                VowelType.HARD -> when (lastChar) {
                    'п' -> root.dropLast(1) + "ба"
                    'к' -> root.dropLast(1) + "га"
                    else -> root + "а"
                }

                VowelType.SOFT -> when (lastChar) {
                    'п' -> root.dropLast(1) + "бә"
                    'к' -> root.dropLast(1) + "гә"
                    else -> root + "ә"
                }
            }
        }
    }

    override fun conjugate(root: String, pronoun: String): String {
        val irregularConjugation = irregularVerbsPresent[root]
        if (irregularConjugation != null) return irregularConjugation[pronoun]!!

        val lastVowelType = findLastVowelType(root)
        val suffix = when {
            (pronoun == "мин") -> "м"
            (pronoun == "син") && lastVowelType == VowelType.HARD -> "сың"
            (pronoun == "син") && lastVowelType == VowelType.SOFT -> "сең"
            (pronoun == "ул") -> ""
            (pronoun == "без") && lastVowelType == VowelType.HARD -> "быз"
            (pronoun == "без") && lastVowelType == VowelType.SOFT -> "без"
            (pronoun == "сез") && lastVowelType == VowelType.HARD -> "сыз"
            (pronoun == "сез") && lastVowelType == VowelType.SOFT -> "сез"
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
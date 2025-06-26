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

class IndefiniteFuture: Verb {
    override fun getConjugationBase(root: String): String {
        val lastVowelType = findLastVowelType(root)
        val lastChar = root.last()
        val vowelsCount = countVowels(root)

        return when {
            vowelsCount > 1 -> when {
                vowels.contains(lastChar) -> root + "р"

                else -> when (lastVowelType) {
                    VowelType.HARD -> when (lastChar) {
                            'п' -> root.dropLast(1) + "быр"

                            'к' -> root.dropLast(1) + "гыр"

                            else -> root + "ыр"
                    }

                    VowelType.SOFT -> when (lastChar) {
                            'п' -> root.dropLast(1) + "бер"

                            'к' -> root.dropLast(1) + "гер"

                            else -> root + "ер"
                    }
                }
            }

            vowelsCount == 1 -> when (lastChar) {
                    'и' -> root + "яр"

                    'й' -> root.dropLast(1) + "яр"

                    'р', 'л' -> when (lastVowelType) {
                            VowelType.HARD -> root + "ыр"

                            VowelType.SOFT -> root + "ер"
                    }

                    else -> when (lastVowelType) {
                            VowelType.HARD -> when (lastChar) {
                                    'п' -> root.dropLast(1) + "бар"

                                    'к' -> root.dropLast(1) + "гар"

                                    else -> root + "ар"
                            }

                            VowelType.SOFT -> when (lastChar) {
                                    'п' -> root.dropLast(1) + "бәр"

                                    'к' -> root.dropLast(1) + "гәр"

                                    else -> root + "әр"
                            }
                    }
            }

            else -> ""
        }
    }

    override fun conjugate(root: String, pronoun: String): String {
        val irregularConjugation = irregularVerbsIndefiniteFuture[root]
        if (irregularConjugation != null) return smashDiftongs(irregularConjugation[pronoun]!!)

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
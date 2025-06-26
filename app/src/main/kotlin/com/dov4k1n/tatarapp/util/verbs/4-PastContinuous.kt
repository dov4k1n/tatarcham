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

class PastContinuous: Verb {
    override fun getConjugationBase(root: String): String {
        return Present().getConjugationBase(root)
    }

    override fun conjugate(root: String, pronoun: String): String {
        val irregularConjugation = irregularVerbsPresent[root]
        val suffix = when {
            (pronoun == "мин") -> " идем"
            (pronoun == "син") -> " идең"
            (pronoun == "ул") -> " иде"
            (pronoun == "без") -> " идек"
            (pronoun == "сез") -> " идегез"
            (pronoun == "алар") -> " иделәр"
            else -> {
                throw IllegalArgumentException(
                    "Unsupported pronoun and vowel combination: " +
                            "pronoun = $pronoun, root = $root"
                )
            }
        }
        return if (irregularConjugation != null) smashDiftongs(irregularConjugation["ул"] + suffix)
        else smashDiftongs(getConjugationBase(root) + suffix)
    }
}
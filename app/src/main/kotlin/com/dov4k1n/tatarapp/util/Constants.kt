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

val tatarAlphabet = "аәбвгдеёжҗзийклмнңоөпрстуүфхһцчшщъыьэюя"
val personalPronouns = listOf("мин", "син", "ул", "без", "сез", "алар")

val hardVowels = listOf('а', 'о', 'у', 'ы')
val softVowels = listOf('ә', 'ө', 'ү', 'и', 'э')
val mutableVowels = listOf('е', 'ю', 'я')
val vowels = softVowels + hardVowels + mutableVowels

val unvoicedConsonants = listOf('п', 'ф', 'к', 'т', 'ш', 'с', 'ч', 'щ', 'ц', 'х', 'һ')
/*val voicedConsonants = listOf('б', 'в', 'г', 'д', 'ж', 'з')
val sonorousConsonants = listOf('м', 'н', 'ң')
val consonants = listOf(
    'б', 'в', 'г', 'д', 'ж', 'җ', 'з', 'й', 'к', 'л', 'м', 'н',
    'ң', 'п', 'р', 'с', 'т', 'ф', 'х', 'һ', 'ц', 'ч', 'ш', 'щ'
)*/

val diftongs = mapOf(
    "йа" to "я",
    "йә" to "я",
    "йя" to "я",
    "йу" to "ю",
    "йү" to "ю",
    "йю" to "ю",
    "йе" to "е",
    "йэ" to "е",
    "йы" to "е",
)

enum class VowelType {
    SOFT, HARD
}
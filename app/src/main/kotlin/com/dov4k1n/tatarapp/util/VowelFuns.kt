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

fun findLastVowelType(word: String): VowelType {
    val lastWord = word.split(" ").last()

    val hasSoftSign = lastWord.contains('ь')
    if (hasSoftSign) return VowelType.SOFT

    for (i in lastWord.length - 1 downTo 0) {
        if (softVowels.contains(lastWord[i])) return VowelType.SOFT
        
        if (hardVowels.contains(lastWord[i])) return VowelType.HARD
    }

    val hasSoftE = lastWord.contains('е') && lastWord[0] != 'е'
    if (hasSoftE) return VowelType.SOFT

    return VowelType.HARD
}

fun countVowels(word: String): Int {
    var count = 0

    for (i in word.length - 1 downTo 0) {
        if (word[i] == ' ') return count

        if (vowels.contains(word[i])) count++
    }

    return count
}

fun smashDiftongs(word: String): String {
    var result = word

    for ((diftong, replacement) in diftongs)
        result = result.replace(diftong, replacement)

    return result
}
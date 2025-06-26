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

import java.io.File

fun tatarAlphabetComparator(): Comparator<String> {
    return Comparator { s1, s2 ->
        val minLen = minOf(s1.length, s2.length)

        for (i in 0 until minLen) {
            val index1 = tatarAlphabet.indexOf(s1[i].lowercaseChar())
            val index2 = tatarAlphabet.indexOf(s2[i].lowercaseChar())
            
            if (index1 != index2) {
                return@Comparator index1 - index2
            }
        }

        return@Comparator s1.length - s2.length
    }
}

fun sortFileAlphabetical(inputFileName: String, outputFileName: String) {
    val inputFile = File(inputFileName)
    val outputFile = File(outputFileName)

    if (!inputFile.exists() || !outputFile.exists()) {
        println("Failed to open files.")
        return
    }

    val words = inputFile.readLines()
    val sortedWords = words.sortedWith(tatarAlphabetComparator())

    outputFile.bufferedWriter().use { writer ->
        for (word in sortedWords) {
            writer.write(word)
            writer.newLine()
        }
    }
}
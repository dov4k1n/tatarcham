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

fun generateExercisesFromFile(
    inputFileName: String, 
    outputFileName: String, 
    conjugate: (String) -> String
) {
    val inputFile = File(inputFileName)
    val outputFilePath = "output/$outputFileName"
    val outputFile = File(outputFilePath)

    if (!inputFile.exists()) {
        println("Failed to open input file.")
        return
    }

    outputFile.bufferedWriter().use { writer ->
        inputFile.useLines { lines ->
            lines.forEach { root ->
                val exercise = conjugate(root)
                writer.write(root + "\n")
                writer.write(exercise)
                writer.newLine()
            }
        }
    }
}

// MAIN.KT EXAMPLE
/*
import util.*

fun main() {
    val present = Present()
    val definitePast = DefinitePast()
    val indefinitePast = IndefinitePast()
    val pastContinuous = PastContinuous()
    val pastPerfect = PastPerfect()
    val definiteFuture = DefiniteFuture()
    val indefiniteFuture = IndefiniteFuture()
    val infinitive = Infinitive()

    val inputFileName = "C:\\Users\\dov4k1n\\Documents\\GitHub\\Other\\TatarConjugator\\src\\main\\kotlin\\input.txt"

    generateExercisesFromFile(
        inputFileName,
        "1-present_output.txt",
        { root -> present.conjugateInAllPronouns(root)}
    )

    generateExercisesFromFile(
        inputFileName,
        "2-definite_past_output.txt",
        { root -> definitePast.conjugateInAllPronouns(root)}
    )

    generateExercisesFromFile(
        inputFileName,
        "3-indefinite_past_output.txt",
        { root -> indefinitePast.conjugateInAllPronouns(root)}
    )

    generateExercisesFromFile(
        inputFileName,
        "4-past_continuous_output.txt",
        { root -> pastContinuous.conjugateInAllPronouns(root)}
    )

    generateExercisesFromFile(
        inputFileName,
        "5-past_perfect_output.txt",
        { root -> pastPerfect.conjugateInAllPronouns(root)}
    )

    generateExercisesFromFile(
        inputFileName,
        "7-definite_future_output.txt",
        { root -> definiteFuture.conjugateInAllPronouns(root)}
    )

    generateExercisesFromFile(
        inputFileName,
        "8-indefinite_future_output.txt",
        { root -> indefiniteFuture.conjugateInAllPronouns(root)}
    )

    generateExercisesFromFile(
        inputFileName,
        "10-infinitive_output.txt",
        { root -> infinitive.conjugate(root)}
    )

    formatToLaTeXEnumerationGroup("1-present_output.txt", "1-Present.tex")
    formatToLaTeXEnumerationGroup("2-definite_past_output.txt", "2-DefinitePast.tex")
    formatToLaTeXEnumerationGroup("3-indefinite_past_output.txt", "3-IndefinitePast.tex")
    formatToLaTeXEnumerationGroup("4-past_continuous_output.txt", "4-PastContinuous.tex")
    formatToLaTeXEnumerationGroup("5-past_perfect_output.txt", "5-PastPerfect.tex")
    formatToLaTeXEnumerationGroup("7-definite_future_output.txt", "7-DefiniteFuture.tex")
    formatToLaTeXEnumerationGroup("8-indefinite_future_output.txt", "8-IndefiniteFuture.tex")
    formatToLaTeXEnumerationGroup("10-infinitive_output.txt", "10-Infinitive.tex")
}
*/
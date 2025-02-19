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
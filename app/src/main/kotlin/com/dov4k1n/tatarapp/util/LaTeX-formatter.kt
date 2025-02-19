package com.dov4k1n.tatarapp.util

import java.io.File

fun formatToLaTeXEnumerationGroup(inputFileName: String, outputFileName: String) {
    val inputFilePath = "output/$inputFileName"
    val inputFile = File(inputFilePath)
    val outputFilePath = "output/tex/$outputFileName"
    val outputFile = File(outputFilePath)

    if (!inputFile.exists()) {
        println("Failed to open files.")
        return
    }

    val lines = inputFile.readLines()
    val groupedLines = mutableListOf<List<String>>()
    val group = mutableListOf<String>()

    for (line in lines) {
        if (line.isNotBlank()) {
            group.add("$line\\\\")
        } else if (group.isNotEmpty()) {
            groupedLines.add(group.toList())
            group.clear()
        }
    }

    outputFile.bufferedWriter().use { writer ->
        writer.write("\\begin{multicols}{4}\n")
        writer.write("\\begin{enumerate}\n")
        for (groupOfLines in groupedLines) {
            writer.write("\\begin{minipage}{\\linewidth}\n")
            writer.write("    \\item\n")
            for (line in groupOfLines) {
                writer.write("    $line\n")
            }
            writer.write("\\end{minipage}\n")
            writer.write("\n")
        }
        writer.write("\\end{enumerate}\n")
        writer.write("\\endmulticols")
    }
}

fun formatToLaTeXEnumeration(inputFileName: String, outputFileName: String) {
    val inputFile = File(inputFileName)
    val outputFile = File(outputFileName)

    if (!inputFile.exists() || !outputFile.exists()) {
        println("Failed to open files.")
        return
    }

    val lines = inputFile.readLines()

    outputFile.bufferedWriter().use { writer ->
        writer.write("\\begin{enumerate}\n")
        for (line in lines) {
            writer.write("    \\item ${line}.\n")
        }
        writer.write("\\end{enumerate}\n\n")
    }
}
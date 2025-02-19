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
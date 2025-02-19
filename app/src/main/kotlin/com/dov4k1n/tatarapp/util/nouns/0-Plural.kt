package com.dov4k1n.tatarapp.util.nouns

import com.dov4k1n.tatarapp.util.VowelType
import com.dov4k1n.tatarapp.util.findLastVowelType

fun toPlural(word: String, trash: String): String {
    val lastVowelType = findLastVowelType(word)

    return when (word.last()) {
        'м', 'н', 'ң' -> when (lastVowelType) {
            VowelType.HARD -> word + "нар"
            VowelType.SOFT -> word + "нәр"
        }

        else -> when (lastVowelType) {
            VowelType.HARD -> word + "лар"
            VowelType.SOFT -> word + "ләр"
        }
    }
}
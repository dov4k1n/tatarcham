package com.dov4k1n.tatarapp.util

import com.dov4k1n.tatarapp.util.verbs.Verb

class DefinitePast: Verb {
    override fun getConjugationBase(root: String): String {
        val lastVowelType = findLastVowelType(root)

        return when {
            unvoicedConsonants.contains(root.last()) -> {
                when (lastVowelType) {
                    VowelType.HARD -> root + "ты"
                    VowelType.SOFT -> root + "те"
                }
            }
            else -> {
                when (lastVowelType) {
                    VowelType.HARD -> root + "ды"
                    VowelType.SOFT -> root + "де"
                }
            }
        }
    }

    override fun conjugate(root: String, pronoun: String): String {
        val lastVowelType = findLastVowelType(root)
        val suffix = when {
            (pronoun == "мин") -> "м"
            (pronoun == "син") -> "ң"
            (pronoun == "ул") -> ""
            (pronoun == "без") -> "к"
            (pronoun == "сез") && lastVowelType == VowelType.HARD -> "гыз"
            (pronoun == "сез") && lastVowelType == VowelType.SOFT -> "гез"
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
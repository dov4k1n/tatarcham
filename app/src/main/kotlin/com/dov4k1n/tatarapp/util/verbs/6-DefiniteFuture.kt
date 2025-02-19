package com.dov4k1n.tatarapp.util

import com.dov4k1n.tatarapp.util.verbs.Verb

class DefiniteFuture: Verb {
    override fun getConjugationBase(root: String): String {
        val lastVowelType = findLastVowelType(root)
        val lastChar = root.last()

        return when (lastChar) {
            'у' -> root + "ачак"

            'ү' -> root + "әчәк"

            'и', 'й' -> when (lastVowelType) {
                VowelType.HARD -> root + "ячак"

                VowelType.SOFT -> root + "ячәк"
            }

            in softVowels -> root + "ячәк"

            in hardVowels -> root + "ячак"

            in mutableVowels -> when (lastVowelType) {
                    VowelType.HARD -> root + "ячак"

                    VowelType.SOFT -> root + "ячәк"
            }

            else -> when (lastVowelType) {
                VowelType.HARD -> when (lastChar) {
                        'п' -> root.dropLast(1) + "бачак"

                        'к' -> root.dropLast(1) + "гачак"

                        else -> root + "ачак"
                }

                VowelType.SOFT -> when (lastChar) {
                        'п' -> root.dropLast(1) + "бәчәк"

                        'к' -> root.dropLast(1) + "гәчәк"

                        else -> root + "әчәк"
                }
            }
        }
    }

    override fun conjugate(root: String, pronoun: String): String {
        val irregularConjugation = irregularVerbsDefiniteFuture[root]
        if (irregularConjugation != null) return smashDiftongs(irregularConjugation[pronoun]!!)

        val lastVowelType = findLastVowelType(root)
        val suffix = when {
            (pronoun == "мин") && lastVowelType == VowelType.HARD -> "мын"
            (pronoun == "мин") && lastVowelType == VowelType.SOFT -> "мен"
            (pronoun == "син") && lastVowelType == VowelType.HARD -> "сың"
            (pronoun == "син") && lastVowelType == VowelType.SOFT -> "сең"
            (pronoun == "ул") -> ""
            (pronoun == "без") && lastVowelType == VowelType.HARD -> "быз"
            (pronoun == "без") && lastVowelType == VowelType.SOFT -> "без"
            (pronoun == "сез") && lastVowelType == VowelType.HARD -> "сыз"
            (pronoun == "сез") && lastVowelType == VowelType.SOFT -> "сез"
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
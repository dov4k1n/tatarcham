package com.dov4k1n.tatarapp.util

import com.dov4k1n.tatarapp.util.verbs.Verb
import com.dov4k1n.tatarapp.util.verbs.irregularVerbs.irregularVerbsGerund

class Gerund: Verb {
    override fun getConjugationBase(root: String): String {
        return ""
    }

    override fun conjugate(root: String, pronoun: String): String {
        val lastVowelType = findLastVowelType(root)
        val lastChar = root.last()

        val result = irregularVerbsGerund[root] ?: when (lastVowelType) {
            VowelType.HARD -> when (lastChar) {
                'п' -> root.dropLast(1) + "бу"
                'к' -> root.dropLast(1) + "гу"
                'ы' -> root.dropLast(1) + "у"
                //'и' -> root + "ю"
                else -> root + "у"
            }
            VowelType.SOFT -> when (lastChar) {
                'п' -> root.dropLast(1) + "бү"
                'к' -> root.dropLast(1) + "гү"
                //'ы' -> root.dropLast(1) + "ү"
                'и' -> root + "ю"
                else -> root + "ү"
            }
        }

        return smashDiftongs(result)
    }
}
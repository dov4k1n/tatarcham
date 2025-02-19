package com.dov4k1n.tatarapp.util

import com.dov4k1n.tatarapp.util.verbs.Verb

class Infinitive: Verb {
    override fun getConjugationBase(root: String): String {
        return IndefiniteFuture().getConjugationBase(root)
    }

    override fun conjugate(root: String, pronoun: String): String {
        val lastVowelType = findLastVowelType(root)

        val irregularConjugation = irregularVerbsIndefiniteFuture[root]
        if (irregularConjugation != null) return when (lastVowelType) {
                VowelType.HARD -> smashDiftongs(irregularConjugation["ул"] + "га")
                VowelType.SOFT -> smashDiftongs(irregularConjugation["ул"] + "гә")
        }

        val result = when (lastVowelType) {
            VowelType.HARD -> getConjugationBase(root) + "га"
            VowelType.SOFT -> getConjugationBase(root) + "гә"
        }
        return smashDiftongs(result)
    }
}
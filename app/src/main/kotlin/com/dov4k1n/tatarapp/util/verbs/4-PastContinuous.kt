package com.dov4k1n.tatarapp.util

import com.dov4k1n.tatarapp.util.verbs.Verb

class PastContinuous: Verb {
    override fun getConjugationBase(root: String): String {
        return Present().getConjugationBase(root)
    }

    override fun conjugate(root: String, pronoun: String): String {
        val irregularConjugation = irregularVerbsPresent[root]
        val suffix = when {
            (pronoun == "мин") -> " идем"
            (pronoun == "син") -> " идең"
            (pronoun == "ул") -> " иде"
            (pronoun == "без") -> " идек"
            (pronoun == "сез") -> " идегез"
            (pronoun == "алар") -> " иделәр"
            else -> {
                throw IllegalArgumentException(
                    "Unsupported pronoun and vowel combination: " +
                            "pronoun = $pronoun, root = $root"
                )
            }
        }
        return if (irregularConjugation != null) smashDiftongs(irregularConjugation["ул"] + suffix)
        else smashDiftongs(getConjugationBase(root) + suffix)
    }
}
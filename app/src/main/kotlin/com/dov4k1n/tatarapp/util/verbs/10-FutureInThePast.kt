package com.dov4k1n.tatarapp.util

import com.dov4k1n.tatarapp.util.verbs.Verb

class FutureInThePast: Verb {
    override fun getConjugationBase(root: String): String {
        return DefiniteFuture().getConjugationBase(root)
    }

    override fun conjugate(root: String, pronoun: String): String {
        val irregularConjugation = irregularVerbsDefiniteFuture[root]
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
        if (irregularConjugation != null) return smashDiftongs(irregularConjugation["ул"]!! + suffix)

        return smashDiftongs(getConjugationBase(root) + suffix)
    }
}
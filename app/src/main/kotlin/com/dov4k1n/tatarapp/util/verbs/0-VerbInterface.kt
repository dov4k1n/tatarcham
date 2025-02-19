package com.dov4k1n.tatarapp.util.verbs

interface Verb {
    fun getConjugationBase(root: String): String
    fun conjugate(root: String, pronoun: String): String
}
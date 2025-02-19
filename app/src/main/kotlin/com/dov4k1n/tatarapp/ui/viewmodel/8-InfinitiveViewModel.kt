package com.dov4k1n.tatarapp.ui.viewmodel

import com.dov4k1n.tatarapp.data.allVerbs
import com.dov4k1n.tatarapp.util.Infinitive

class InfinitiveViewModel
    : ConjugationViewModel(
    wordSet = allVerbs,
    findCorrectAnswer = { root: String, pronoun: String ->
        Infinitive().conjugate(
            root = root,
            pronoun = pronoun
        )
    }
)
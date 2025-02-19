package com.dov4k1n.tatarapp.ui.viewmodel

import com.dov4k1n.tatarapp.data.allVerbs
import com.dov4k1n.tatarapp.util.IndefinitePast

class IndefinitePastViewModel
    : ConjugationViewModel(
    wordSet = allVerbs,
    findCorrectAnswer = { root: String, pronoun: String ->
        IndefinitePast().conjugate(
            root = root,
            pronoun = pronoun
        )
    }
)
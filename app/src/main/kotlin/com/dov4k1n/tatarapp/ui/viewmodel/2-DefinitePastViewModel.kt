package com.dov4k1n.tatarapp.ui.viewmodel

import com.dov4k1n.tatarapp.data.allVerbs
import com.dov4k1n.tatarapp.util.DefinitePast

class DefinitePastViewModel
    : ConjugationViewModel(
    wordSet = allVerbs,
    findCorrectAnswer = { root: String, pronoun: String ->
        DefinitePast().conjugate(
            root = root,
            pronoun = pronoun
        )
    }
)
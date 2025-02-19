package com.dov4k1n.tatarapp.ui.viewmodel

import com.dov4k1n.tatarapp.data.allVerbs
import com.dov4k1n.tatarapp.util.IndefiniteFuture

class IndefiniteFutureViewModel
    : ConjugationViewModel(
    wordSet = allVerbs,
    findCorrectAnswer = { root: String, pronoun: String ->
        IndefiniteFuture().conjugate(
            root = root,
            pronoun = pronoun
        )
    }
)
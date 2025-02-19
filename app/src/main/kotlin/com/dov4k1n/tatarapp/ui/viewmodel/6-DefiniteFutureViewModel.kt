package com.dov4k1n.tatarapp.ui.viewmodel

import com.dov4k1n.tatarapp.data.allVerbs
import com.dov4k1n.tatarapp.util.DefiniteFuture

class DefiniteFutureViewModel
    : ConjugationViewModel(
    wordSet = allVerbs,
    findCorrectAnswer = { root: String, pronoun: String ->
        DefiniteFuture().conjugate(
            root = root,
            pronoun = pronoun
        )
    }
)
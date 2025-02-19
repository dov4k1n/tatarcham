package com.dov4k1n.tatarapp.ui.viewmodel

import com.dov4k1n.tatarapp.data.allVerbs
import com.dov4k1n.tatarapp.util.FutureInThePast


class FutureInThePastViewModel
    : ConjugationViewModel(
    wordSet = allVerbs,
    findCorrectAnswer = { root: String, pronoun: String ->
        FutureInThePast().conjugate(
            root = root,
            pronoun = pronoun
        )
    }
)
package com.dov4k1n.tatarapp.ui.viewmodel.morphology

import com.dov4k1n.tatarapp.data.allNouns
import com.dov4k1n.tatarapp.ui.viewmodel.ConjugationViewModel
import com.dov4k1n.tatarapp.util.nouns.toPlural

class PluralViewModel
    : ConjugationViewModel(
    wordSet = allNouns,
    findCorrectAnswer = { word: String, trash: String ->
        toPlural(word = word, trash = trash)
    }
)
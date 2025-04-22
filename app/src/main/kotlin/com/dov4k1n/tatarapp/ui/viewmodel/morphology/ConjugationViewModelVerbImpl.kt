package com.dov4k1n.tatarapp.ui.viewmodel.morphology

import com.dov4k1n.tatarapp.data.allVerbs
import com.dov4k1n.tatarapp.ui.viewmodel.ConjugationViewModel
import com.dov4k1n.tatarapp.util.DefiniteFuture
import com.dov4k1n.tatarapp.util.DefinitePast
import com.dov4k1n.tatarapp.util.FutureInThePast
import com.dov4k1n.tatarapp.util.Gerund
import com.dov4k1n.tatarapp.util.IndefiniteFuture
import com.dov4k1n.tatarapp.util.IndefinitePast
import com.dov4k1n.tatarapp.util.Infinitive
import com.dov4k1n.tatarapp.util.PastContinuous
import com.dov4k1n.tatarapp.util.PastPerfect
import com.dov4k1n.tatarapp.util.Present

class PresentViewModel
    : ConjugationViewModel(
    wordSet = allVerbs,
    findCorrectAnswer = { root: String, pronoun: String ->
        Present().conjugate(
            root = root,
            pronoun = pronoun
        )
    }
)

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

class PastContinuousViewModel
    : ConjugationViewModel(
    wordSet = allVerbs,
    findCorrectAnswer = { root: String, pronoun: String ->
        PastContinuous().conjugate(
            root = root,
            pronoun = pronoun
        )
    }
)

class PastPerfectViewModel
    : ConjugationViewModel(
    wordSet = allVerbs,
    findCorrectAnswer = { root: String, pronoun: String ->
        PastPerfect().conjugate(
            root = root,
            pronoun = pronoun
        )
    }
)

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

class GerundViewModel
    : ConjugationViewModel(
    wordSet = allVerbs,
    findCorrectAnswer = { root: String, pronoun: String ->
        Gerund().conjugate(
            root = root,
            pronoun = pronoun
        )
    }
)

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
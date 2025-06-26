/*
 * Tatarcham v0.2, tatar language learning app for Android.
 * Copyright (C) 2023-2025 Ayzat Rizatdinov <ddov4k1n at gmail dot com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see https://www.gnu.org/licenses/.
 *
 */

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
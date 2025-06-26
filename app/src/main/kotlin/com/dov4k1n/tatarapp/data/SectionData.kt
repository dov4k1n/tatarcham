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

package com.dov4k1n.tatarapp.data

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CrueltyFree
import androidx.compose.material.icons.outlined.DoubleArrow
import androidx.compose.material.icons.outlined.GTranslate
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.MilitaryTech
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.RamenDining
import androidx.compose.material.icons.outlined.Scale
import androidx.compose.material.icons.outlined.SensorOccupied
import androidx.compose.material.icons.outlined.Work
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.vector.ImageVector
import com.dov4k1n.tatarapp.R
import com.dov4k1n.tatarapp.data.morphologydescriptions.MorphologyDescriptionData
import com.dov4k1n.tatarapp.data.morphologydescriptions.VerbDescriptionData
import com.dov4k1n.tatarapp.data.morphologydescriptions.presentDescriptionData
import com.dov4k1n.tatarapp.data.morphologydescriptions.SectionType
import com.dov4k1n.tatarapp.data.morphologydescriptions.definitePastDescriptionData
import com.dov4k1n.tatarapp.data.morphologydescriptions.indefinitePastDescriptionData
import com.dov4k1n.tatarapp.data.morphologydescriptions.pastContinuousDescriptionData

interface DescriptionProvider {
    @Composable fun Description()
}

open class VerbDescriptionProvider : DescriptionProvider {
    @Composable
    override fun Description() {

    }

}

@Composable
fun PresentDescription() {}

@Composable
fun DefinitePastDescription() {}

@Composable
fun IndefinitePastDescription() {}

@Composable
fun PastContinuousDescription() {}

@Composable
fun PastPerfectDescription() {}

@Composable
fun DefiniteFutureDescription() {}

@Composable
fun IndefiniteFutureDescription() {}

@Composable
fun InfinitiveDescription() {}

@Composable
fun GerundDescription() {}

@Composable
fun FutureInThePastDescription() {}

@Composable
fun NounPluralDescription() {}

@Composable
fun NounGenitiveDescription() {}

@Composable
fun NounDativeDescription() {}

@Composable
fun NounAccusativeDescription() {}

@Composable
fun NounAblativeDescription() {}

@Composable
fun NounLocativeDescription() {}

@Composable
fun AdjectiveEzafeDescription() {}

@Composable
fun AdjectiveComparisonDescription() {}

@Composable
fun VowelHarmonyDescription() {}

@Composable
fun RoundingHarmonyDescription() {}

@Composable
fun HardSoftKGDescription() {}

@Composable
fun VoicingKPDescription() {}

@Composable
fun DiphthongsDescription() {}

@Composable
fun SwadeshListDescription() {}

@Composable
fun TopWordsDescription() {}

@Composable
fun TopVerbsDescription() {}

@Composable
fun TopNounsDescription() {}

@Composable
fun TopAdjectivesDescription() {}

@Composable
fun PeopleDescription() {}

@Composable
fun AnimalsDescription() {}

@Composable
fun FoodDescription() {}

@Composable
fun ProfessionsDescription() {}

sealed class LevelData(
    open val icon: ImageVector = Icons.Outlined.PlayArrow,
    val route: String,
    @StringRes val nameAddress: Int,
    val description: @Composable () -> Unit
) {
    // Verbs
    data object Present : LevelData(
        route = "present",
        nameAddress = R.string.verb_present,
        description = { PresentDescription() })

    data object DefinitePast : LevelData(
        route = "definite past",
        nameAddress = R.string.verb_definite_past,
        description = { DefinitePastDescription() })

    data object IndefinitePast : LevelData(
        route = "indefinite past",
        nameAddress = R.string.verb_indefinite_past,
        description = { IndefinitePastDescription() })

    data object PastContinuous : LevelData(
        route = "past continuous",
        nameAddress = R.string.verb_past_continuous,
        description = { PastContinuousDescription() })

    data object PastPerfect : LevelData(
        route = "past perfect",
        nameAddress = R.string.verb_past_perfect,
        description = { PastPerfectDescription() })

    data object DefiniteFuture : LevelData(
        route = "definite future",
        nameAddress = R.string.verb_definite_future,
        description = { DefiniteFutureDescription() })

    data object IndefiniteFuture : LevelData(
        route = "indefinite future",
        nameAddress = R.string.verb_indefinite_future,
        description = { IndefiniteFutureDescription() })

    data object Infinitive : LevelData(
        route = "infinitive",
        nameAddress = R.string.verb_infinitive,
        description = { InfinitiveDescription() })

    data object Gerund : LevelData(
        route = "gerund",
        nameAddress = R.string.verb_gerund,
        description = { GerundDescription() })

    data object FutureInThePast : LevelData(
        route = "future in the past",
        nameAddress = R.string.verb_future_in_the_past,
        description = { FutureInThePastDescription() })

    // Nouns
    data object NounPlural : LevelData(
        route = "plural",
        nameAddress = R.string.noun_plural,
        description = { NounPluralDescription() })

    data object NounGenitive : LevelData(
        route = "genitive",
        nameAddress = R.string.noun_genitive_case,
        description = { NounGenitiveDescription() })

    data object NounDative : LevelData(
        route = "dative",
        nameAddress = R.string.noun_dative_case,
        description = { NounDativeDescription() })

    data object NounAccusative : LevelData(
        route = "accusative",
        nameAddress = R.string.noun_accusative_case,
        description = { NounAccusativeDescription() })

    data object NounAblative : LevelData(
        route = "ablative",
        nameAddress = R.string.noun_ablative_case,
        description = { NounAblativeDescription() })

    data object NounLocative : LevelData(
        route = "locative",
        nameAddress = R.string.noun_locative_case,
        description = { NounLocativeDescription() })

    // Adjectives
    data object AdjectiveEzafe : LevelData(
        route = "ezafe",
        nameAddress = R.string.adjective_ezafe,
        description = { AdjectiveEzafeDescription() })

    data object AdjectiveComparison : LevelData(
        route = "comparison",
        nameAddress = R.string.adjective_degrees_of_comparison,
        description = { AdjectiveComparisonDescription() })

    // Phonology
    data object VowelHarmony : LevelData(
        route = "vowel harmony",
        nameAddress = R.string.vowel_harmony,
        description = { VowelHarmonyDescription() })

    data object RoundingHarmony : LevelData(
        route = "rounding harmony",
        nameAddress = R.string.rounding_harmony,
        description = { RoundingHarmonyDescription() })

    data object HardSoftKG : LevelData(
        route = "hard/soft k/g",
        nameAddress = R.string.hard_soft_k_g,
        description = { HardSoftKGDescription() })

    data object VoicingKP : LevelData(
        route = "voicing k/p",
        nameAddress = R.string.voicing_k_p,
        description = { VoicingKPDescription() })

    data object Diphthongs : LevelData(
        route = "diphthongs",
        nameAddress = R.string.diftongs,
        description = { DiphthongsDescription() })

    // Dictionary
    data object SwadeshList : LevelData(
        route = "swadesh list",
        nameAddress = R.string.swadesh_list,
        icon = Icons.Outlined.MilitaryTech,
        description = { SwadeshListDescription() })

    data object TopWords : LevelData(
        route = "top words",
        nameAddress = R.string.top_words,
        icon = Icons.Outlined.GTranslate,
        description = { TopWordsDescription() })

    data object TopVerbs : LevelData(route = "top verbs", nameAddress = R.string.top_verbs, icon = Icons.Outlined.DoubleArrow, description = { TopVerbsDescription() })
    data object TopNouns : LevelData(route = "top nouns", nameAddress = R.string.top_nouns, icon = Icons.Outlined.SensorOccupied, description = { TopNounsDescription() })
    data object TopAdjectives : LevelData(route = "top adjectives", nameAddress = R.string.top_adjectives, icon = Icons.Outlined.Scale, description = { TopAdjectivesDescription() })
    data object People : LevelData(route = "people", nameAddress = R.string.people, icon = Icons.Outlined.Groups, description = { PeopleDescription() })
    data object Animals : LevelData(route = "animals", nameAddress = R.string.animals, icon = Icons.Outlined.CrueltyFree, description = { AnimalsDescription() })
    data object Food : LevelData(route = "food", nameAddress = R.string.food, icon = Icons.Outlined.RamenDining, description = { FoodDescription() })
    data object Professions : LevelData(route = "professions", nameAddress = R.string.professions, icon = Icons.Outlined.Work, description = { ProfessionsDescription() })

}

@Immutable
open class SectionData(
    open val type: SectionType = SectionType.Undefined,
    open val icon: ImageVector = Icons.Outlined.PlayArrow,
    @StringRes open val nameAddress: Int,
    open val description: MorphologyDescriptionData = MorphologyDescriptionData(),
    @StringRes open val moreDescription: Int = R.string.empty_string,
    open val id: Int
)


data class SectionVerbData(
    override val type: SectionType = SectionType.Verb,
    @StringRes override val nameAddress: Int,
    override val description: VerbDescriptionData = VerbDescriptionData(),
    @StringRes override val moreDescription: Int = R.string.empty_string,
    override val id: Int
) : SectionData(
    type = type,
    nameAddress = nameAddress,
    description = description,
    moreDescription = moreDescription,
    id = id
)

val sectionVerb: List<SectionVerbData> = listOf(
    SectionVerbData(
        nameAddress = R.string.verb_present,
        description = presentDescriptionData,
        moreDescription = R.string.verb_present_description,
        id = 1,
    ),
    SectionVerbData(
        nameAddress = R.string.verb_definite_past,
        description = definitePastDescriptionData,
        moreDescription = R.string.verb_definite_past_description,
        id = 2
    ),
    SectionVerbData(
        nameAddress = R.string.verb_indefinite_past,
        description = indefinitePastDescriptionData,
        moreDescription = R.string.verb_indefinite_past_description,
        id = 3
    ),
    SectionVerbData(
        nameAddress = R.string.verb_past_continuous,
        description = pastContinuousDescriptionData,
        moreDescription = R.string.verb_past_continuous_description,
        id = 4
    ),
    SectionVerbData(
        nameAddress = R.string.verb_past_perfect,
        moreDescription = R.string.verb_past_perfect_description,
        id = 5
    ),
    SectionVerbData(
        nameAddress = R.string.verb_definite_future,
        moreDescription = R.string.verb_definite_future_description,
        id = 6
    ),
    SectionVerbData(
        nameAddress = R.string.verb_indefinite_future,
        moreDescription = R.string.verb_indefinite_future_description,
        id = 7
    ),
    SectionVerbData(
        nameAddress = R.string.verb_infinitive,
        moreDescription = R.string.verb_infinitive_description,
        id = 8
    ),
    SectionVerbData(
        nameAddress = R.string.verb_gerund,
        moreDescription = R.string.verb_gerund_description,
        id = 9
    ),
    SectionVerbData(
        nameAddress = R.string.verb_future_in_the_past,
        moreDescription = R.string.verb_future_in_the_past_description,
        id = 10
    )
)

val sectionNoun: List<SectionData> = listOf(
    SectionData(
        nameAddress = R.string.noun_plural,
        moreDescription = R.string.noun_plural_description,
        id = 1
    ),
    SectionData(
        nameAddress = R.string.noun_genitive_case,
        moreDescription = R.string.noun_genitive_case_description,
        id = 2
    ),
    SectionData(
        nameAddress = R.string.noun_dative_case,
        moreDescription = R.string.noun_dative_case_description,
        id = 3
    ),
    SectionData(
        nameAddress = R.string.noun_accusative_case,
        moreDescription = R.string.noun_accusative_case_description,
        id = 4
    ),
    SectionData(
        nameAddress = R.string.noun_ablative_case,
        moreDescription = R.string.noun_ablative_case_description,
        id = 5
    ),
    SectionData(
        nameAddress = R.string.noun_locative_case,
        moreDescription = R.string.noun_locative_case_description,
        id = 6
    )
)

val sectionAdjective: List<SectionData> = listOf(
    SectionData(
        nameAddress = R.string.adjective_ezafe,
        moreDescription = R.string.adjective_ezafe_description,
        id = 1
    ),
    SectionData(
        nameAddress = R.string.adjective_degrees_of_comparison,
        moreDescription = R.string.adjective_degrees_of_comparison_description,
        id = 2
    )
)

val sectionHarmonies: List<SectionData> = listOf(
    SectionData(
        nameAddress = R.string.vowel_harmony,
        moreDescription = R.string.vowel_harmony_description,
        id = 1
    ),
    SectionData(
        nameAddress = R.string.rounding_harmony,
        moreDescription = R.string.rounding_harmony_description,
        id = 2
    ),
    SectionData(
        nameAddress = R.string.hard_soft_k_g,
        moreDescription = R.string.hard_soft_k_g_description,
        id = 3
    ),
    SectionData(
        nameAddress = R.string.voicing_k_p,
        moreDescription = R.string.voicing_k_p_description,
        id = 4
    ),
    SectionData(
        nameAddress = R.string.diftongs,
        moreDescription = R.string.diftongs_description,
        id = 5
    )
)

val sectionDictionary: List<SectionData> = listOf(
    SectionData(
        nameAddress = R.string.swadesh_list,
        icon = Icons.Outlined.MilitaryTech,
        moreDescription = R.string.words_207,
        id = 1
    ),
    SectionData(
        nameAddress = R.string.top_words,
        icon= Icons.Outlined.GTranslate,
        moreDescription = R.string.words_1000,
        id = 2
    ),
    SectionData(
        nameAddress = R.string.top_verbs,
        icon = Icons.Outlined.DoubleArrow,
        moreDescription = R.string.words_100,
        id = 3
    ),
    SectionData(
        nameAddress = R.string.top_nouns,
        icon = Icons.Outlined.SensorOccupied,
        moreDescription = R.string.words_100,
        id = 4
    ),
    SectionData(
        nameAddress = R.string.top_adjectives,
        icon = Icons.Outlined.Scale,
        moreDescription = R.string.words_100,
        id = 5
    ),
    SectionData(
        nameAddress = R.string.people,
        icon = Icons.Outlined.Groups,
        moreDescription = R.string.words_25,
        id = 6
    ),
    SectionData(
        nameAddress = R.string.animals,
        icon = Icons.Outlined.CrueltyFree,
        moreDescription = R.string.words_40,
        id = 7
    ),
    SectionData(
        nameAddress = R.string.food,
        icon = Icons.Outlined.RamenDining,
        moreDescription = R.string.words_50,
        id = 8
    ),
    SectionData(
        nameAddress = R.string.professions,
        icon = Icons.Outlined.Work,
        moreDescription = R.string.words_30,
        id = 9
    )
)

//val sectionPronoun: List<SectionData> = listOf(
//    SectionData(
//        nameAddress = R.string.pronoun_possessive,
//        moreDescription = R.string.pronoun_possessive_description,
//        id = 1
//    ),
//    SectionData(
//        nameAddress = R.string.pronoun_demonstrative,
//        moreDescription = R.string.pronoun_demonstrative_description,
//        id = 2
//    ),
//    SectionData(
//        nameAddress = R.string.pronoun_interrogative,
//        moreDescription = R.string.pronoun_interrogative_description,
//        id = 3
//    ),
//    SectionData(
//        nameAddress = R.string.pronoun_determinative,
//        moreDescription = R.string.pronoun_determinative_description,
//        id = 4
//    ),
//    SectionData(
//        nameAddress = R.string.pronoun_indefinite,
//        moreDescription = R.string.pronoun_indefinite_description,
//        id = 5
//    ),
//    SectionData(
//        nameAddress = R.string.pronoun_negative,
//        moreDescription = R.string.pronoun_negative_description,
//        id = 6
//    )
//)
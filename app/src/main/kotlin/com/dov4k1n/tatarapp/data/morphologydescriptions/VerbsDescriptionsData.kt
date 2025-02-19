package com.dov4k1n.tatarapp.data.morphologydescriptions

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.dov4k1n.tatarapp.R

enum class SectionType {
    Undefined,
    Verb,
    Noun,
    Adjective,
}

@Immutable
open class MorphologyDescriptionData

data class VerbDescriptionData(
    @StringRes val actionTime: Int = R.string.empty_string,
    @StringRes val question: Int = R.string.empty_string,
    @StringRes val conjugationBaseAffixOne: Int = R.string.empty_string,
    @StringRes val conjugationBaseCaseOne: Int = R.string.empty_string,
    @StringRes val conjugationBaseAffixTwo: Int = R.string.empty_string,
    @StringRes val conjugationBaseCaseTwo: Int = R.string.empty_string,
    @StringRes val personalAffixes: Int = R.string.empty_string,
    @StringRes val negativeForm: Int = R.string.empty_string
) : MorphologyDescriptionData()

val presentDescriptionData = VerbDescriptionData(
    actionTime = R.string.present_description_action_time,
    question = R.string.present_description_question,
    conjugationBaseAffixOne = R.string.present_description_conjugation_base_affix_one,
    conjugationBaseCaseOne = R.string.present_description_conjugation_base_case_one,
    conjugationBaseAffixTwo = R.string.present_description_conjugation_base_affix_two,
    conjugationBaseCaseTwo = R.string.present_description_conjugation_base_case_two,
    personalAffixes = R.string.present_description_personal_affixes,
    negativeForm = R.string.present_description_negative_form
)

val definitePastDescriptionData = VerbDescriptionData(
    actionTime = R.string.definite_past_description_action_time,
    question = R.string.definite_past_description_question,
    conjugationBaseAffixOne = R.string.definite_past_description_conjugation_base_affix_one,
    conjugationBaseCaseOne = R.string.definite_past_description_conjugation_base_case_one,
    conjugationBaseAffixTwo = R.string.definite_past_description_conjugation_base_affix_two,
    conjugationBaseCaseTwo = R.string.definite_past_description_conjugation_base_case_two,
    personalAffixes = R.string.definite_past_description_personal_affixes,
    negativeForm = R.string.definite_past_description_negative_form
)

val indefinitePastDescriptionData = VerbDescriptionData(
    actionTime = R.string.indefinite_past_description_action_time,
    question = R.string.indefinite_past_description_question,
    conjugationBaseAffixOne = R.string.indefinite_past_description_conjugation_base_affix_one,
    conjugationBaseCaseOne = R.string.indefinite_past_description_conjugation_base_case_one,
    conjugationBaseAffixTwo = R.string.indefinite_past_description_conjugation_base_affix_two,
    conjugationBaseCaseTwo = R.string.indefinite_past_description_conjugation_base_case_two,
    personalAffixes = R.string.indefinite_past_description_personal_affixes,
    negativeForm = R.string.indefinite_past_description_negative_form
)

val pastContinuousDescriptionData = VerbDescriptionData(
    actionTime = R.string.past_continuous_description_action_time,
    question = R.string.past_continuous_description_question,
    conjugationBaseAffixOne = R.string.past_continuous_description_conjugation_base_affix_one,
    conjugationBaseCaseOne = R.string.past_continuous_description_conjugation_base_case_one,
    conjugationBaseAffixTwo = R.string.past_continuous_description_conjugation_base_affix_two,
    conjugationBaseCaseTwo = R.string.past_continuous_description_conjugation_base_case_two,
    personalAffixes = R.string.past_continuous_description_personal_affixes,
    negativeForm = R.string.past_continuous_description_negative_form
)
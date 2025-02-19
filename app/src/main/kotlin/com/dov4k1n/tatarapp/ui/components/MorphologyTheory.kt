package com.dov4k1n.tatarapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dov4k1n.tatarapp.R
import com.dov4k1n.tatarapp.ui.theme.TatarAppTheme


@Preview
@Composable
fun PreviewVerbTheoryWithBase() {
    TatarAppTheme(useDarkTheme = true) {
        VerbTheoryWithBase(
            levelName = R.string.verb_present,
            conjugationBaseAffixOne = R.string.verb_present_base_affix_one,
            conjugationBaseAffixTwo = R.string.verb_present_base_affix_two,
            conjugationBaseCaseOne = R.string.verb_present_base_case_one,
            conjugationBaseCaseTwo = R.string.verb_present_base_case_two,
            personalAffixes = R.string.verb_present_affixes,
            examples = R.string.verb_present_examples,
        )
    }
}

@Composable
fun VerbTheoryWithBase(
    levelName: Int = R.string.empty_string,
    conjugationBaseAffixOne: Int = R.string.empty_string,
    conjugationBaseAffixTwo: Int = R.string.empty_string,
    conjugationBaseCaseOne: Int = R.string.empty_string,
    conjugationBaseCaseTwo: Int = R.string.empty_string,
    personalAffixes: Int = R.string.empty_string,
    examples: Int = R.string.empty_string,
) {
    val paddingAfterSection = 40.dp
    val paddingAfterSubtitle = 16.dp
    val subtitleColor = colorScheme.onBackground

    Column {

        Text(
            text = stringResource(levelName),
            style = typography.titleMedium,
            color = colorScheme.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(bottom = 24.dp)
                .fillMaxWidth()
        )

        Text(
            text = stringResource(R.string.formation_of_the_conjugated_stem),
            style = typography.bodyLarge,
            color = subtitleColor,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(bottom = paddingAfterSubtitle)
                .fillMaxWidth()
        )
        ConjugationBase(
            modifier = Modifier.padding(bottom = paddingAfterSection),
            conjugationBaseAffixOne = conjugationBaseAffixOne,
            conjugationBaseAffixTwo = conjugationBaseAffixTwo,
            conjugationBaseCaseOne = conjugationBaseCaseOne,
            conjugationBaseCaseTwo = conjugationBaseCaseTwo
        )

        Text(
            text = stringResource(R.string.personal_affixes),
            style = typography.bodyLarge,
            color = subtitleColor,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(bottom = paddingAfterSubtitle)
                .fillMaxWidth()
        )
        PersonalAffixes(
            modifier = Modifier.padding(bottom = paddingAfterSection),
            affixesColumn = personalAffixes
        )

        Text(
            text = stringResource(R.string.hard_hard_soft_soft),
            style = typography.bodyMedium,
            color = colorScheme.onBackground,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = paddingAfterSection)
        )

        Text(
            text = stringResource(R.string.examples),
            style = typography.bodyLarge,
            color = subtitleColor,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(bottom = paddingAfterSubtitle)
                .fillMaxWidth()
        )
        Text(
            text = stringResource(examples),
            style = typography.bodyMedium,
            color = colorScheme.primary,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun PreviewPresentTheory() {
    TatarAppTheme(useDarkTheme = true) {
        PresentTheory()
    }
}
@Composable
fun PresentTheory() {
    VerbTheoryWithBase(
        levelName = R.string.verb_present,
        conjugationBaseAffixOne = R.string.verb_present_base_affix_one,
        conjugationBaseAffixTwo = R.string.verb_present_base_affix_two,
        conjugationBaseCaseOne = R.string.verb_present_base_case_one,
        conjugationBaseCaseTwo = R.string.verb_present_base_case_two,
        personalAffixes = R.string.verb_present_affixes,
        examples = R.string.verb_present_examples,
    )
}

@Preview
@Composable
fun PreviewDefinitePastTheory() {
    TatarAppTheme(useDarkTheme = true) {
        DefinitePastTheory()
    }
}
@Composable
fun DefinitePastTheory() {
    VerbTheoryWithBase(
        levelName = R.string.verb_definite_past,
        conjugationBaseAffixOne = R.string.verb_definite_past_base_affix_one,
        conjugationBaseAffixTwo = R.string.verb_definite_past_base_affix_two,
        conjugationBaseCaseOne = R.string.verb_definite_past_base_case_one,
        conjugationBaseCaseTwo = R.string.verb_definite_past_base_case_two,
        personalAffixes = R.string.verb_definite_past_affixes,
        examples = R.string.verb_definite_past_examples,
    )
}

@Preview
@Composable
fun PreviewIndefinitePastTheory() {
    TatarAppTheme(useDarkTheme = true) {
        IndefinitePastTheory()
    }
}
@Composable
fun IndefinitePastTheory() {
    VerbTheoryWithBase(
        levelName = R.string.verb_indefinite_past,
        conjugationBaseAffixOne = R.string.verb_indefinite_past_base_affix_one,
        conjugationBaseAffixTwo = R.string.verb_indefinite_past_base_affix_two,
        conjugationBaseCaseOne = R.string.verb_indefinite_past_base_case_one,
        conjugationBaseCaseTwo = R.string.verb_indefinite_past_base_case_two,
        personalAffixes = R.string.verb_indefinite_past_affixes,
        examples = R.string.verb_indefinite_past_examples,
    )
}

@Preview
@Composable
fun PreviewPastContinuousTheory() {
    TatarAppTheme(useDarkTheme = true) {
        PastContinuousTheory()
    }
}
@Composable
fun PastContinuousTheory() {
    VerbTheoryWithBase(
        levelName = R.string.verb_past_continuous,
        conjugationBaseAffixOne = R.string.verb_past_continuous_base_affix_one,
        conjugationBaseAffixTwo = R.string.verb_past_continuous_base_affix_two,
        conjugationBaseCaseOne = R.string.verb_past_continuous_base_case_one,
        conjugationBaseCaseTwo = R.string.verb_past_continuous_base_case_two,
        personalAffixes = R.string.verb_past_continuous_affixes,
        examples = R.string.verb_past_continuous_examples,
    )
}
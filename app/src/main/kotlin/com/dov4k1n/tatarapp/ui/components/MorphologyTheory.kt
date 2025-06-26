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

package com.dov4k1n.tatarapp.ui.components

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dov4k1n.tatarapp.R
import com.dov4k1n.tatarapp.ui.theme.TatarAppTheme
import dev.jeziellago.compose.markdowntext.MarkdownText
import java.io.BufferedReader
import java.io.InputStreamReader


@Composable
fun assetFileReader(context: Context, fileName: String): String {
    val assetManager = context.assets
    val bufferedReader = BufferedReader(InputStreamReader(assetManager.open(fileName)))
    val content = buildString {
        var line: String?
        while (bufferedReader.readLine().also { line = it } != null) {
            append(line)
            append("\n")
        }
    }
    bufferedReader.close()
    return content
}

@Preview
@Composable
fun PreviewDarkMD() {
    TatarAppTheme() {
        MDExample()
    }
}

@Preview
@Composable
fun PreviewLightMD() {
    TatarAppTheme() {
        MDExample()
    }
}

@Composable
fun MDExample(
    fileName: String = "alphabet-latin.md"
) {
    val context = LocalContext.current
    val markdownContent = assetFileReader(context, fileName)
    Scaffold() { innerPadding ->
        val x = innerPadding

    MarkdownText(
        markdown = markdownContent,
//        modifier = Modifier.padding(innerPadding),
        truncateOnTextOverflow = true,
        syntaxHighlightColor = Color(0xFF191F26),
        syntaxHighlightTextColor = Color(0xFFFFB454)
    )
    }
}


@Preview
@Composable
fun PreviewVerbTheoryWithBase() {
    TatarAppTheme() {
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
    TatarAppTheme() {
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
    TatarAppTheme() {
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
    TatarAppTheme() {
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
    TatarAppTheme() {
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
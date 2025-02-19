package com.dov4k1n.tatarapp.ui.screens

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Typeface
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.BulletSpan
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.text.style.SubscriptSpan
import android.text.style.SuperscriptSpan
import android.text.style.TypefaceSpan
import android.text.style.UnderlineSpan
import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dov4k1n.tatarapp.R
import com.dov4k1n.tatarapp.ui.theme.Shapes
import com.dov4k1n.tatarapp.ui.viewmodel.ConjugationViewModel

@Composable
fun VerbConjugationScreen(
    levelName: String? = null,
    levelInstructionText: String? = null,
    viewModel: ConjugationViewModel = viewModel(),
    skipPrefix: Boolean = false,
    @StringRes someInfo: Int = 0,
) {
    val sessionUiState by viewModel.uiState.collectAsState()
    val smallPadding8dp = dimensionResource(R.dimen.padding_small_8dp)
    val mediumPadding16dp = dimensionResource(R.dimen.padding_medium_16dp)
    var cardFronted by remember { mutableStateOf(true) }

    val score = sessionUiState.score
    val currentWordCount = sessionUiState.currentWordCount

    val percentage = if (currentWordCount != 1)
        (score.toDouble() / (currentWordCount.toDouble() - 1) * 100).toInt()
    else 0

    val context = LocalContext.current
    val sessionStats = stringResource(
        R.string.session_stats_share_message,
        score,
        percentage
    )

    Column(
        modifier = Modifier
            .padding(vertical = smallPadding8dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScoreStatus(
            score = score,
            percentage = percentage,
            sessionStats = sessionStats,
            onSendButtonClicked = {
                shareSessionStats(
                    context = context,
                    sessionStats = sessionStats
                )
            },
        )
        if (levelName != null) {
            Text(
                text = levelName,
                style = typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = smallPadding8dp, vertical = mediumPadding16dp)
                .imePadding(),
        ) {
            VerbLayout(
                currentVerb = sessionUiState.currentWord,
                currentPrefix = sessionUiState.currentPrefix,
                skipPrefix = skipPrefix,
                cardFronted = cardFronted,
                onCardClicked = { cardFronted = !cardFronted },
                userAnswer = viewModel.userAnswer,
                previousUserAnswer = viewModel.previousUserAnswer,
                verbCount = sessionUiState.currentWordCount,
                totalWords = sessionUiState.totalWords,
                onUserTypeChanged = { viewModel.updateUserAnswer(it) },
                onKeyboardDone = { viewModel.checkUserAnswer() },
                isTypeWrong = sessionUiState.isTypedAnsWrong,
                levelInstructionText = levelInstructionText,
                someInfo = someInfo,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                //.padding(smallPadding8dp)
            )
            AnimatedVisibility(cardFronted) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(smallPadding8dp),
                    verticalArrangement = Arrangement.spacedBy(mediumPadding16dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize(),
                        onClick = { viewModel.checkUserAnswer() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorScheme.primaryContainer,
                            contentColor = colorScheme.onPrimaryContainer
                        ),
                        shape = Shapes.medium
                    ) {
                        Text(
                            text = stringResource(R.string.submit),
                            style = typography.headlineMedium
                        )
                    }
                    OutlinedButton(
                        onClick = { viewModel.skipWord() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize(),
                        shape = Shapes.medium
                    ) {
                        Text(
                            text = stringResource(R.string.skip),
                            style = typography.headlineMedium
                        )
                    }
                }
            }
            AnimatedVisibility(!cardFronted) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = smallPadding8dp, vertical = mediumPadding16dp),
                    verticalArrangement = Arrangement.spacedBy(mediumPadding16dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedButton(
                        onClick = { cardFronted = !cardFronted },
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize(),
                        shape = Shapes.medium
                    ) {
                        Text(
                            text = stringResource(R.string.gotcha),
                            style = typography.headlineMedium
                        )
                    }
                }
            }
        }
    }

    if (sessionUiState.isGameOver) {
        FinalScoreDialog(
            score = sessionUiState.score,
            onPlayAgain = {
                viewModel.resetGame()
            }
        )
    }
}

@Composable
fun VerbLayout(
    currentVerb: String,
    currentPrefix: String,
    userAnswer: String,
    previousUserAnswer: String,
    verbCount: Int,
    totalWords: Int,
    onUserTypeChanged: (String) -> Unit,
    onKeyboardDone: () -> Unit,
    isTypeWrong: Boolean,
    modifier: Modifier = Modifier,
    skipPrefix: Boolean = false,
    cardFronted: Boolean = true,
    onCardClicked: () -> Unit,
    levelInstructionText: String? = null,
    @StringRes someInfo: Int,
) {
    val smallPadding8dp = dimensionResource(R.dimen.padding_small_8dp)
    val mediumPadding16dp = dimensionResource(R.dimen.padding_medium_16dp)
    Card(
        modifier = modifier
            .padding(smallPadding8dp)
            .clickable(onClick = onCardClicked),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorScheme.primaryContainer,
        ),
    ) {
        AnimatedVisibility(cardFronted) {
            Column(
                verticalArrangement = Arrangement.spacedBy(mediumPadding16dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(smallPadding8dp)
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioLowBouncy,
                            stiffness = Spring.StiffnessMedium
                        )
                    )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { },
                        modifier = Modifier.padding(vertical = 4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.History,
                            contentDescription = "history",
                        )
                    }
                    Spacer(Modifier.weight(1f))
                    Text(
                        modifier = Modifier
                            .clip(shapes.medium)
                            .background(colorScheme.onPrimary),
                            //.padding(horizontal = 10.dp, vertical = 4.dp),
                        text = stringResource(R.string.verb_count, verbCount, totalWords),
                        style = typography.titleMedium
                    )
                }
                Text(
                    text = currentVerb,
                    style = typography.displaySmall
                )
                if (levelInstructionText != null) {
                    Row(
                        Modifier
                            .clickable {
                                onCardClicked()
                            }
                            .padding(4.dp)
                    ) {
                        val infiniteTransition = rememberInfiniteTransition(label = "")
                        val scale by infiniteTransition.animateColor(
                            initialValue = colorScheme.onPrimaryContainer, targetValue = colorScheme.error,
                            animationSpec = infiniteRepeatable(tween(2000), RepeatMode.Reverse),
                            label = ""
                        )
                        Text(
                            text = levelInstructionText,
                            textAlign = TextAlign.Center,
                            style = typography.titleMedium,
                            color = scale
                        )
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(start = 4.dp)
                                .align(Alignment.CenterVertically),
                            tint = scale
                        )
                    }
                }
                OutlinedTextField(
                    value = userAnswer,
                    onValueChange = onUserTypeChanged,
                    singleLine = false,
                    shape = shapes.medium,
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(
                            text = if (isTypeWrong) stringResource(R.string.wrong_guess)
                                   else stringResource(R.string.enter_your_word),
                            style = typography.labelSmall
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = colorScheme.background,
                        unfocusedContainerColor = colorScheme.background,
                        disabledContainerColor = colorScheme.background,
                        focusedTextColor = colorScheme.onBackground
                    ),
                    textStyle = typography.displaySmall,
                    isError = isTypeWrong,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { onKeyboardDone() }
                    ),
                    prefix = {
                        Text(
                            text = if (skipPrefix) "" else "$currentPrefix ",
                            style = typography.displaySmall
                        )
                    },
                    supportingText = {
                        if (isTypeWrong) {
                            Text(
                                text = stringResource(
                                    R.string.your_wrong_type,
                                    previousUserAnswer
                                ),
                                style = typography.bodySmall
                            )
                        }
                    }
                )
            }
        }
        AnimatedVisibility(!cardFronted) {
            Column(
                verticalArrangement = Arrangement.spacedBy(smallPadding8dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(smallPadding8dp)
            ) {
                Text(
                    text = annotatedStringResource(someInfo),
                    style = typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun ScoreStatus(
    score: Int,
    percentage: Int,
    sessionStats: String,
    onSendButtonClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorScheme.secondaryContainer,
        ),
        modifier = modifier
            .clickable { onSendButtonClicked(sessionStats) }
    ) {
        Text(
            text = stringResource(id = R.string.score, score, percentage),
            style = typography.headlineMedium,
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_small_4dp))
                .background(color = colorScheme.secondaryContainer)
        )
    }
}

private fun shareSessionStats(
    context: Context,
    sessionStats: String
) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, sessionStats)
    }

    context.startActivity(
        Intent.createChooser(
            intent,
            context.getString(R.string.session_stats_share_title)
        )
    )
}

@Composable
private fun FinalScoreDialog(
    score: Int,
    onPlayAgain: () -> Unit,
    modifier: Modifier = Modifier
) {
    val activity = (LocalContext.current as Activity)

    AlertDialog(
        onDismissRequest = { /*TODO*/ },
        confirmButton = {
            TextButton(
                onClick = onPlayAgain
            ) {
                Text(
                    text = stringResource(R.string.play_again)
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    activity.finish()
                }
            ) {
                Text(text = stringResource(R.string.exit))
            }
        },
        title = { Text(text = stringResource(R.string.congratulations)) },
        text = { Text(text = stringResource(R.string.you_scored, score)) },
        modifier = modifier,
    )
}

//SECOND METHOD
@Composable
@ReadOnlyComposable
private fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}

/*fun Spanned.toHtmlWithoutParagraphs(): String {
    return HtmlCompat.toHtml(this, HtmlCompat.TO_HTML_PARAGRAPH_LINES_CONSECUTIVE)
        .substringAfter("<p dir=\"ltr\">").substringBeforeLast("</p>")
}*/

/*fun Resources.getText(@StringRes id: Int, vararg args: Any): CharSequence {
    val escapedArgs = args.map {
        if (it is Spanned) it.toHtmlWithoutParagraphs() else it
    }.toTypedArray()
    val resource = SpannedString(getText(id))
    val htmlResource = resource.toHtmlWithoutParagraphs()
    val formattedHtml = String.format(htmlResource, *escapedArgs)
    return HtmlCompat.fromHtml(formattedHtml, HtmlCompat.FROM_HTML_MODE_LEGACY)
}*/

/*@Composable
fun annotatedStringResource(@StringRes id: Int, vararg formatArgs: Any): AnnotatedString {
    val resources = resources()
    val density = LocalDensity.current
    return remember(id, formatArgs) {
        val text = resources.getText(id, *formatArgs)
        spannableStringToAnnotatedString(text, density)
    }
}*/

@Composable
fun annotatedStringResource(@StringRes id: Int): AnnotatedString {
    val resources = resources()
    val density = LocalDensity.current
    return remember(id) {
        val text = resources.getText(id)
        spannableStringToAnnotatedString(text, density)
    }
}

private fun spannableStringToAnnotatedString(
    text: CharSequence,
    density: Density
): AnnotatedString {
    return if (text is Spanned) {
        with(density) {
            buildAnnotatedString {
                append((text.toString()))
                text.getSpans(0, text.length, Any::class.java).forEach {
                    val start = text.getSpanStart(it)
                    val end = text.getSpanEnd(it)
                    when (it) {
                        is StyleSpan -> when (it.style) {
                            Typeface.NORMAL -> addStyle(
                                SpanStyle(
                                    fontWeight = FontWeight.Normal,
                                    fontStyle = FontStyle.Normal
                                ),
                                start,
                                end
                            )
                            Typeface.BOLD -> addStyle(
                                SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontStyle = FontStyle.Normal
                                ),
                                start,
                                end
                            )
                            Typeface.ITALIC -> addStyle(
                                SpanStyle(
                                    fontWeight = FontWeight.Normal,
                                    fontStyle = FontStyle.Italic
                                ),
                                start,
                                end
                            )
                            Typeface.BOLD_ITALIC -> addStyle(
                                SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontStyle = FontStyle.Italic
                                ),
                                start,
                                end
                            )
                        }
                        is TypefaceSpan -> addStyle(
                            SpanStyle(
                                fontFamily = when (it.family) {
                                    FontFamily.SansSerif.name -> FontFamily.SansSerif
                                    FontFamily.Serif.name -> FontFamily.Serif
                                    FontFamily.Monospace.name -> FontFamily.Monospace
                                    FontFamily.Cursive.name -> FontFamily.Cursive
                                    else -> FontFamily.Default
                                }
                            ),
                            start,
                            end
                        )
                        is BulletSpan -> {
                            Log.d("StringResources", "BulletSpan not supported yet")
                            addStyle(SpanStyle(), start, end)
                        }
                        is AbsoluteSizeSpan -> addStyle(
                            SpanStyle(fontSize = if (it.dip) it.size.dp.toSp() else it.size.toSp()),
                            start,
                            end
                        )
                        is RelativeSizeSpan -> addStyle(
                            SpanStyle(fontSize = it.sizeChange.em),
                            start,
                            end
                        )
                        is StrikethroughSpan -> addStyle(
                            SpanStyle(textDecoration = TextDecoration.LineThrough),
                            start,
                            end
                        )
                        is UnderlineSpan -> addStyle(
                            SpanStyle(textDecoration = TextDecoration.Underline),
                            start,
                            end
                        )
                        is SuperscriptSpan -> addStyle(
                            SpanStyle(baselineShift = BaselineShift.Superscript),
                            start,
                            end
                        )
                        is SubscriptSpan -> addStyle(
                            SpanStyle(baselineShift = BaselineShift.Subscript),
                            start,
                            end
                        )
                        is ForegroundColorSpan -> addStyle(
                            SpanStyle(color = Color(it.foregroundColor)),
                            start,
                            end
                        )
                        else -> addStyle(SpanStyle(), start, end)
                    }
                }
            }
        }
    } else {
        AnnotatedString(text.toString())
    }
}

@Preview
@Composable
fun PreviewHistoryDialog(
    onDismiss: () -> Unit = {}
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onDismiss
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = null
                    )
                }
                Text(
                    text = "History",
                    textAlign = TextAlign.Center,
                    style = typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            Column(
                Modifier.selectableGroup()
            ) {

            }
        }
    }
}
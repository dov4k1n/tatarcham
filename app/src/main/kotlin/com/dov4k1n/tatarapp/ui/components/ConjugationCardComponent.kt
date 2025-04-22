package com.dov4k1n.tatarapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dov4k1n.tatarapp.R
import com.dov4k1n.tatarapp.ui.state.ShakingState
import com.dov4k1n.tatarapp.ui.state.rememberShakingState
import com.dov4k1n.tatarapp.ui.state.shakable
import com.dov4k1n.tatarapp.ui.theme.Montserrat
import com.dov4k1n.tatarapp.ui.theme.Shapes
import com.dov4k1n.tatarapp.ui.theme.TatarAppTheme
import kotlinx.coroutines.launch

@Preview
@Composable
fun PreviewConjugationCard() {
    TatarAppTheme(useDarkTheme = true) {
        ConjugationCard(
            levelName = "Present",
            taskMessage = "Put the verb into the correct form",
            theory = R.string.empty_string,
            currentVerb = "кил",
            skipPrefix = false,
            currentPrefix = "без",
            userAnswer = "киләбез",
            previousUserAnswer = "калабыз",
            onUserTypeChanged = {},
            onKeyboardDone = {},
            isTypeWrong = true
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConjugationCard(
    levelName: String,
    taskMessage: String,
    theory: Int = R.string.empty_string,
    newTheory: @Composable () -> Unit = {},
    currentVerb: String,
    skipPrefix: Boolean,
    currentPrefix: String,
    userAnswer: String,
    previousUserAnswer: String,
    onUserTypeChanged: (String) -> Unit,
    onKeyboardDone: () -> Unit,
    isTypeWrong: Boolean,
    modifier: Modifier = Modifier,
) {
    var textFieldFocusState by remember { mutableStateOf(false) }

    val shakingState = rememberShakingState(
        strength = ShakingState.Strength.Normal,
        directions = ShakingState.Directions.RIGHT_THEN_LEFT
    )

    if (isTypeWrong) {
        LaunchedEffect(shakingState) {
            shakingState.shake(animationDuration = 10)
        }
    }

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState,
            containerColor = colorScheme.primaryContainer,
        ) {

            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
//                    .verticalScroll(rememberScrollState())
//                    .horizontalScroll(rememberScrollState())
            ) {

                if (theory == R.string.empty_string) {
                    item {
                        newTheory()
                    }
                } else {
                    item {

                    Text(
                        text = levelName,
                        style = typography.titleMedium,
                        color = colorScheme.primary,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(bottom = 24.dp)
                            .fillMaxWidth()
                    )
                    Text(
                        text = stringResource(theory),
                        style = typography.bodyMedium,
                        color = colorScheme.primary,
                    )
                    }
                }
                item {

                Button(
                    onClick = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showBottomSheet = false
                            }
                        }
                    },
                    colors = ButtonColors(
                        contentColor = colorScheme.primary,
                        containerColor = colorScheme.tertiary,
                        disabledContentColor = colorScheme.tertiary,
                        disabledContainerColor = colorScheme.primary
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                ) {
                    Text(
                        text = stringResource(R.string.gotcha),
                        style = typography.bodyMedium
                    )
                }
                }
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        color = colorScheme.onSurfaceVariant,
                    )
                ) {
                    append(taskMessage)
                }

                appendLine(":")

                withStyle(
                    SpanStyle(
                        color = colorScheme.primary,
                    )
                ) {
                    append(levelName)
                }
            },//taskMessage,
            style = typography.titleLarge,
            color = colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
        )

        Card(
            shape = Shapes.extraLarge,
            border = BorderStroke(
                width = 3.dp,
                color = colorScheme.primaryContainer
            ),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 4.dp
            ),
            colors = CardColors(
                contentColor = colorScheme.primary,
                containerColor = colorScheme.primaryContainer,
                disabledContentColor = colorScheme.primary,
                disabledContainerColor = Color.Transparent,
            ),
            modifier = Modifier
//                .fillMaxSize()
                .padding(bottom = 32.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = currentVerb,
                    style = typography.headlineSmall,
                    color = colorScheme.primary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 48.dp)
                )
            }
        }

        TextField(
            value = userAnswer,
            onValueChange = onUserTypeChanged,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { onKeyboardDone() }
            ),
            singleLine = false,
            shape = Shapes.large,
            placeholder = {
                if (!textFieldFocusState) {
                    Text(
                        text = stringResource(R.string.enter_your_word),
                        color = colorScheme.onSurfaceVariant,
                        style = typography.titleSmall
                    )
                }
            },
            prefix = {
                if (!skipPrefix && textFieldFocusState) {
                    Text(
                        text = "$currentPrefix ",
                        style = typography.titleLarge,
                        color = colorScheme.onSurfaceVariant,
                    )
                }
            },
            trailingIcon = {
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    IconButton(
                        onClick = { showBottomSheet = true },
                        modifier = Modifier.padding(end = 4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.MenuBook,
                            contentDescription = "theory",
                            tint = colorScheme.onBackground,
                            modifier = Modifier
                                .size(24.dp)
                        )
                    }
                    IconButton(
                        onClick = { onKeyboardDone() },
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Send,
                            contentDescription = "send",
                            tint = if (textFieldFocusState) colorScheme.onError else colorScheme.onBackground,
                            modifier = Modifier
                                .size(24.dp)
                        )
                    }
                }
            },
            supportingText = {
                if (isTypeWrong) {
                    Text(
                        text = stringResource(R.string.your_wrong_type, previousUserAnswer),
                        style = typography.bodySmall
                    )
                }
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = colorScheme.primaryContainer,
                unfocusedContainerColor = colorScheme.primaryContainer,
                focusedTextColor = colorScheme.secondary,
                unfocusedTextColor = colorScheme.secondary,
                cursorColor = colorScheme.secondary,
                focusedPlaceholderColor = Color.Transparent,
                unfocusedPlaceholderColor = colorScheme.onSurfaceVariant,
                focusedPrefixColor = colorScheme.onSurfaceVariant,
                unfocusedPrefixColor = Color.Transparent,
                focusedSupportingTextColor = colorScheme.error,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            textStyle = TextStyle(
                color = colorScheme.secondary,
                fontFamily = Montserrat,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 28.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .shakable(shakingState)
                .onFocusChanged { textFieldFocusState = it.isFocused },
        )
    }
}
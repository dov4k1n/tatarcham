package com.dov4k1n.tatarapp.ui.components

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.os.LocaleListCompat
import com.dov4k1n.tatarapp.R
import com.dov4k1n.tatarapp.data.local.ThemeMode
import com.dov4k1n.tatarapp.ui.theme.TatarAppTheme
import kotlinx.coroutines.launch

@Preview
@Composable
fun PreviewLanguageChooser() {
    TatarAppTheme(ThemeMode.Dark) {
        LanguageChooserDialog {}
    }
}

private fun changeLocale(
    old: MutableState<String>,
    new: String
) {
    AppCompatDelegate.setApplicationLocales(
        LocaleListCompat.forLanguageTags(new)
    )
    old.value = new
}

@Composable
fun LanguageChooserDialog(
    onDismiss: () -> Unit
) {
    val scope = rememberCoroutineScope()

    val tatar = stringResource(R.string.tatar)
    val russian = stringResource(R.string.russian)
    val english = stringResource(R.string.english)

    val localeOptions = mapOf(
        tatar to "tt",
        russian to "ru",
        english to "en"
    )

    val context = LocalContext.current

    val defaultLocale = context
        .resources
        .configuration
        .locales
        .get(0)
        .language

    val currentLocale = remember { mutableStateOf(defaultLocale) }

    Dialog(
        onDismissRequest = onDismiss
    ) {
        Surface(
            color = colorScheme.primaryContainer,
            shape = shapes.medium,
        ) {
            Column(
                modifier = Modifier.padding(8.dp),
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
                            contentDescription = null,
                            tint = colorScheme.onBackground
                        )
                    }
                    Text(
                        text = stringResource(R.string.language),
                        style = typography.titleMedium,
                        color = colorScheme.onBackground
                    )
                }

                Column(
                    Modifier.selectableGroup()
                ) {
                    localeOptions.forEach { (selectionLocale, localeTag) ->
                        val isSelected = currentLocale.value == localeTag
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .selectable(
                                    selected = isSelected,
                                    onClick = {
                                        onDismiss()
                                        scope.launch {
                                            changeLocale(
                                                old = currentLocale,
                                                new = localeTag
                                            )
                                        }
                                    },
                                    role = Role.RadioButton
                                )
                                .padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = isSelected,
                                onClick = null,
                                colors = RadioButtonColors(
                                    selectedColor =
                                        colorScheme.primary,
                                    unselectedColor =
                                        colorScheme.onSurfaceVariant,
                                    disabledSelectedColor =
                                        colorScheme.background,
                                    disabledUnselectedColor =
                                        colorScheme.background
                                )
                            )
                            when (localeTag) {
                                "tt" -> {
                                    Image(
                                        painter = painterResource(
                                            id = R.drawable.tatarstan
                                        ),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .padding(start = 8.dp)
                                            .size(28.dp)
                                    )
                                }
                                "ru" -> {
                                    Image(
                                        painter = painterResource(
                                            id = R.drawable.russia
                                        ),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .padding(start = 8.dp)
                                            .size(28.dp)
                                    )
                                }
                                "en" -> {
                                    Image(
                                        painter = painterResource(
                                            id = R.drawable.united_states
                                        ),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .padding(start = 8.dp)
                                            .size(28.dp)
                                    )
                                }
                            }
                            Text(
                                text = selectionLocale,
                                style = typography.bodyLarge,
                                color = if (isSelected)
                                            colorScheme.primary
                                        else
                                            colorScheme.onBackground,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

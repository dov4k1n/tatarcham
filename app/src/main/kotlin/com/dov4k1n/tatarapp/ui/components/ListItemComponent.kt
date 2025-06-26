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

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.outlined.Code
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.LocalUseFallbackRippleImplementation
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dov4k1n.tatarapp.R
import com.dov4k1n.tatarapp.data.SectionData
import com.dov4k1n.tatarapp.data.SectionVerbData
import com.dov4k1n.tatarapp.data.local.ThemeMode
import com.dov4k1n.tatarapp.data.morphologydescriptions.SectionType
import com.dov4k1n.tatarapp.data.sectionVerb
import com.dov4k1n.tatarapp.ui.theme.ListItemShape
import com.dov4k1n.tatarapp.ui.theme.TatarAppTheme

@Preview
@Composable
fun PreviewExpandableListItem() {
    TatarAppTheme(themeMode = ThemeMode.Dark) {
        ExpandableListItem(
            iconColor = colorScheme.onSecondary,
            sectionData = sectionVerb[1],
            shape = ListItemShape.medium,
            onIconClick = {}
        )
    }
}

@Preview
@Composable
fun PreviewNonExpandableListItem() {
    TatarAppTheme(themeMode = ThemeMode.Dark) {
        NonExpandableListItem(
            title = "Source code",
            subtitle = "on GitHub under GPL 3.0 license",
            icon = Icons.Outlined.Code,
            iconColor = colorScheme.onBackground,
            shape = ListItemShape.small,
            onClick = {}
        )
    }
}

@Composable
fun NonExpandableListItem(
    title: String,
    titleColor: Color = colorScheme.primary,
    icon: ImageVector,
    iconColor: Color,
    shape: Shape,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    subtitle: String = "",
    progressBar: @Composable () -> Unit = {}
) {
    Card(
        colors = CardColors(
            containerColor = colorScheme.primaryContainer,
            contentColor = colorScheme.primary,
            disabledContainerColor = colorScheme.background,
            disabledContentColor = colorScheme.background
        ),
        shape = shape,
        modifier = modifier
            .padding(horizontal = 16.dp)
            .clickable { onClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(vertical = 8.dp)
        ) {
            IconButton(
                onClick = { onClick() }
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = title,
                    color = titleColor,
                    style = typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                if (subtitle.isNotEmpty()) {
                    Text(
                        text = subtitle,
                        style = typography.bodySmall,
                        color = colorScheme.onBackground,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
            progressBar()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandableListItem(
    iconColor: Color,
    sectionData: SectionData,
    shape: Shape,
    onIconClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }

    val expandedColor by animateColorAsState(
        targetValue =
            if (expanded)
                colorScheme.onPrimaryContainer
            else
                colorScheme.primaryContainer,
        label = ""
    )
    CompositionLocalProvider(LocalRippleConfiguration provides null) {
        Card(
            shape = shape,
            modifier = modifier
                .padding(horizontal = 16.dp)
                .clickable { expanded = !expanded }
        ) {
            Column(
                modifier = Modifier
                    .background(color = expandedColor)
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioNoBouncy,
                            stiffness = Spring.StiffnessMedium
                        )
                    )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .background(color = expandedColor)
                        .padding(vertical = 8.dp)
                ) {
                    CompositionLocalProvider(LocalUseFallbackRippleImplementation provides true) {
                        IconButton(
                            onClick = { onIconClick() }
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.PlayArrow,
                                contentDescription = null,
                                tint = iconColor,
                                modifier = Modifier
                                    .padding(horizontal = 8.dp)
                            )
                        }
                    }
                    Text(
                        text = stringResource(sectionData.nameAddress),
                        style = typography.bodyMedium,
                        color = Color(0xFFFFFFFF),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .weight(1f)
                    )
                    CompositionLocalProvider(LocalUseFallbackRippleImplementation provides true) {
                        IconButton(
                            onClick = { expanded = !expanded }
                        ) {
                            Icon(
                                imageVector =
                                    if (expanded)
                                        Icons.Filled.ExpandLess
                                    else
                                        Icons.Filled.ExpandMore,
                                contentDescription = null,
                                tint = Color(0xFFFFFFFF),
                                modifier = Modifier
                                    .padding(horizontal = 8.dp)
                            )
                        }
                    }
                }

                AnimatedVisibility(expanded) {
                    Column {
                        when (sectionData.type) {
                            SectionType.Verb -> {
                                VerbItemDescription(
                                    sectionData = sectionData as SectionVerbData,
                                    modifier = Modifier
                                        .padding(horizontal = 8.dp)
                                )
                            }
                            SectionType.Noun -> {

                            }
                            SectionType.Adjective -> {

                            }
                            else -> {

                            }
                        }
                        CompositionLocalProvider(LocalUseFallbackRippleImplementation provides true) {
                            ElevatedButton(
                                onClick = { onIconClick() },
                                colors = ButtonColors(
                                    contentColor = colorScheme.secondary,
                                    containerColor = colorScheme.primaryContainer,
                                    disabledContentColor = colorScheme.primary,
                                    disabledContainerColor = colorScheme.onError
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(alignment = Alignment.CenterHorizontally)
                                    .padding(start = 8.dp, end = 8.dp, bottom = 12.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.play),
                                    style = typography.titleMedium,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
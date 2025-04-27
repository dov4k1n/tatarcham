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
import com.dov4k1n.tatarapp.data.morphologydescriptions.SectionType
import com.dov4k1n.tatarapp.data.sectionVerb
import com.dov4k1n.tatarapp.ui.theme.ListItemShape
import com.dov4k1n.tatarapp.ui.theme.TatarAppTheme

@Preview
@Composable
fun PreviewNewLevelItem() {
    TatarAppTheme(useDarkTheme = true) {
        NewLevelItem(
            playArrowColor = colorScheme.onSecondary,
            sectionData = sectionVerb[1],
            shape = ListItemShape.medium,
            onPlayButtonClicked = {}
        )
    }
}

@Preview
@Composable
fun PreviewNonExpandableLevelItem() {
    TatarAppTheme(useDarkTheme = true) {
        NonExpandableLevelItem(
            levelHeading = "Source code",
            itemIcon = Icons.Outlined.Code,
            itemIconColor = colorScheme.primary,
            shape = ListItemShape.small,
            onPlayButtonClicked = {}
        )
    }
}

@Composable
fun NonExpandableLevelItem(
    levelHeading: String,
    itemIcon: ImageVector,
    itemIconColor: Color,
    shape: Shape,
    onPlayButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
    levelSubheading: String = "",
    progressComposable: @Composable () -> Unit = {}
) {
    Card(
        shape = shape,
        modifier = modifier
            .padding(horizontal = 16.dp)
            .clickable { onPlayButtonClicked() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(color = colorScheme.primaryContainer)
                .padding(vertical = 8.dp)
        ) {
            IconButton(
                onClick = { onPlayButtonClicked() }
            ) {
                Icon(
                    imageVector = itemIcon,
                    contentDescription = null,
                    tint = itemIconColor,
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
                    text = levelHeading,
                    style = typography.bodyMedium,
                    color = Color(0xFFFFFFFF),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                if (levelSubheading.isNotEmpty()) {
                    Text(
                        text = levelSubheading,
                        style = typography.bodySmall,
                        color = colorScheme.onBackground,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
            progressComposable()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewLevelItem(
    playArrowColor: Color,
    sectionData: SectionData,
    shape: Shape,
    onPlayButtonClicked: () -> Unit,
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
                            onClick = { onPlayButtonClicked() }
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.PlayArrow,
                                contentDescription = null,
                                tint = playArrowColor,
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
                                onClick = { onPlayButtonClicked() },
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
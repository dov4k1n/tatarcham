package com.dov4k1n.tatarapp.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dov4k1n.tatarapp.R
import com.dov4k1n.tatarapp.data.SectionData
import com.dov4k1n.tatarapp.data.sectionNoun
import com.dov4k1n.tatarapp.data.sectionVerb

@Composable
fun LevelsListScreen(
    onPlayButtonClicked: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(vertical = 4.dp)
    ) {
        item {
            Text(
                text = stringResource(R.string.verb),
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium_16dp))
            )
        }
        items(
            items = sectionVerb,
            key = {
                it.nameAddress
            }
        ) {
            LevelItem(
                sectionData = it,
                onPlayButtonClicked = { onPlayButtonClicked(it.nameAddress) },
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium_16dp))
            )
        }

        item {
            Text(
                text = stringResource(R.string.noun),
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium_16dp))
            )
        }
        items(
            items = sectionNoun,
            key = {
                it.nameAddress
            }
        ) {
            LevelItem(
                sectionData = it,
                onPlayButtonClicked = { onPlayButtonClicked(it.nameAddress) },
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium_16dp))
            )
        }
    }
}

@Composable
fun LevelItem(
    sectionData: SectionData,
    onPlayButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val color by animateColorAsState(
        targetValue = if (expanded) MaterialTheme.colorScheme.tertiaryContainer
                      else MaterialTheme.colorScheme.primaryContainer,
        label = ""
    )
    Card(
        modifier = modifier
            .clickable { expanded = !expanded }
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
                .background(color = color)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_small_4dp)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LevelItemPlayButtonWithName(
                    levelName = stringResource(sectionData.nameAddress),
                    onPlayButtonClicked = onPlayButtonClicked,
                    modifier = Modifier.weight(10f)
                )
                Spacer(modifier = Modifier.weight(0.01f))
                LevelItemExpandButton(
                    expanded = expanded,
                    onExpandButtonClicked = { expanded = !expanded }
                )
            }
            if (expanded) {
                LevelInformation(
                    levelInfo = annotatedStringResource(sectionData.moreDescription),
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium_16dp),
                        end = dimensionResource(R.dimen.padding_medium_16dp),
                        bottom = dimensionResource(R.dimen.padding_medium_16dp)
                    )
                )
                LevelItemPlayTextButton(
                    onPlayButtonClicked = onPlayButtonClicked,
                    modifier = Modifier
                        .fillMaxWidth()
                        //.padding(dimensionResource(R.dimen.padding_small_4dp))
                        .align(alignment = Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@Composable
private fun LevelItemPlayButtonWithName(
    levelName: String,
    onPlayButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(
                end = dimensionResource(id = R.dimen.padding_medium_16dp),
                top = dimensionResource(id = R.dimen.padding_small_4dp),
                bottom = dimensionResource(id = R.dimen.padding_small_4dp)
            )
    ) {
        IconButton(
            onClick = onPlayButtonClicked
        ) {
            Icon(
                imageVector = Icons.Outlined.PlayArrow,
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
            )
        }
        Text(
            text = levelName,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .padding(start = dimensionResource(R.dimen.padding_small_4dp))
                .align(Alignment.CenterVertically)
        )
    }
}

@Composable
private fun LevelItemExpandButton(
    expanded: Boolean,
    onExpandButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onExpandButtonClicked,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
private fun LevelItemPlayTextButton(
    onPlayButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clickable(onClick = onPlayButtonClicked)
            .padding(vertical = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.play),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.displaySmall
        )
    }
}

@Composable
fun LevelInformation(
    levelInfo: AnnotatedString,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.about),
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = levelInfo,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
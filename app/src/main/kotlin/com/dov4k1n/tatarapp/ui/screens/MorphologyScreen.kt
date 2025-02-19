package com.dov4k1n.tatarapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DoubleArrow
import androidx.compose.material.icons.outlined.Scale
import androidx.compose.material.icons.outlined.SensorOccupied
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dov4k1n.tatarapp.R
import com.dov4k1n.tatarapp.data.BottomAppBarItems
import com.dov4k1n.tatarapp.data.sectionAdjective
import com.dov4k1n.tatarapp.data.sectionNoun
import com.dov4k1n.tatarapp.data.sectionVerb
import com.dov4k1n.tatarapp.ui.components.NewLevelItem
import com.dov4k1n.tatarapp.ui.components.TheoryBlock
import com.dov4k1n.tatarapp.ui.theme.ListItemShape
import com.dov4k1n.tatarapp.ui.theme.TatarAppTheme

@Preview
@Composable
fun PreviewMorphologyScreen() {
    TatarAppTheme(useDarkTheme = true) {
        MorphologyScreen {

        }
    }
}

@Composable
fun MorphologyScreen(
    onPlayButtonClicked: (Int) -> Unit
) {
    LazyColumn {
        item {
            TheoryBlock(
                icon = BottomAppBarItems.Morphology.selectedIcon,
                heading = stringResource(R.string.bottom_bar_morphology),
                headingColor = colorScheme.onSecondary,
                subheading = stringResource(id = R.string.introduction_subheading),
                modifier = Modifier
                    .padding(bottom = 8.dp)
            )
        }


        item {
            TheoryBlock(
                icon = Icons.Outlined.DoubleArrow,
                heading = stringResource(id = R.string.verb),
                headingColor = colorScheme.onSecondary,
                subheading = stringResource(id = R.string.verb_subheading),
            )
        }
        items(
            items = sectionVerb,
            key = { it.nameAddress }
        ) {
            when (it.id) {
                1 -> {
                    NewLevelItem(
                        playArrowColor = colorScheme.onSecondary,
                        sectionData = it,
                        onPlayButtonClicked = { onPlayButtonClicked(it.nameAddress) },
                        shape = ListItemShape.small,
                        modifier = Modifier
                            .padding(bottom = 2.dp)
                    )
                }

                sectionVerb.size -> {
                    NewLevelItem(
                        playArrowColor = colorScheme.onSecondary,
                        sectionData = it,
                        onPlayButtonClicked = { onPlayButtonClicked(it.nameAddress) },
                        shape = ListItemShape.large,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }

                else -> {
                    NewLevelItem(
                        playArrowColor = colorScheme.onSecondary,
                        sectionData = it,
                        onPlayButtonClicked = { onPlayButtonClicked(it.nameAddress) },
                        shape = ListItemShape.medium,
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                }
            }
        }


        item {
            TheoryBlock(
                icon = Icons.Outlined.SensorOccupied,
                heading = stringResource(id = R.string.noun),
                headingColor = colorScheme.onSecondary,
                subheading = stringResource(id = R.string.noun_subheading),
            )
        }
        items(
            items = sectionNoun,
            key = { it.nameAddress }
        ) {
            when (it.id) {
                1 -> {
                    NewLevelItem(
                        playArrowColor = colorScheme.onSecondary,
                        sectionData = it,
                        onPlayButtonClicked = { onPlayButtonClicked(it.nameAddress) },
                        shape = ListItemShape.small,
                        modifier = Modifier
                            .padding(bottom = 2.dp)
                    )
                }

                sectionNoun.size -> {
                    NewLevelItem(
                        playArrowColor = colorScheme.onSecondary,
                        sectionData = it,
                        onPlayButtonClicked = { onPlayButtonClicked(it.nameAddress) },
                        shape = ListItemShape.large,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }

                else -> {
                    NewLevelItem(
                        playArrowColor = colorScheme.onSecondary,
                        sectionData = it,
                        onPlayButtonClicked = { onPlayButtonClicked(it.nameAddress) },
                        shape = ListItemShape.medium,
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                }
            }
        }

        item {
            TheoryBlock(
                icon = Icons.Outlined.Scale,
                heading = stringResource(id = R.string.adjective),
                headingColor = colorScheme.onSecondary,
                subheading = stringResource(id = R.string.adjective_subheading),
            )
        }
        items(
            items = sectionAdjective,
            key = { it.nameAddress }
        ) {
            when (it.id) {
                1 -> {
                    NewLevelItem(
                        playArrowColor = colorScheme.onSecondary,
                        sectionData = it,
                        onPlayButtonClicked = { onPlayButtonClicked(it.nameAddress) },
                        shape = ListItemShape.small,
                        modifier = Modifier
                            .padding(bottom = 2.dp)
                    )
                }

                sectionAdjective.size -> {
                    NewLevelItem(
                        playArrowColor = colorScheme.onSecondary,
                        sectionData = it,
                        onPlayButtonClicked = { onPlayButtonClicked(it.nameAddress) },
                        shape = ListItemShape.large,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }

                else -> {
                    NewLevelItem(
                        playArrowColor = colorScheme.onSecondary,
                        sectionData = it,
                        onPlayButtonClicked = { onPlayButtonClicked(it.nameAddress) },
                        shape = ListItemShape.medium,
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                }
            }
        }
    }
}
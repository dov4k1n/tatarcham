package com.dov4k1n.tatarapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MusicNote
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dov4k1n.tatarapp.R
import com.dov4k1n.tatarapp.data.BottomAppBarItems
import com.dov4k1n.tatarapp.data.sectionHarmonies
import com.dov4k1n.tatarapp.ui.components.NewLevelItem
import com.dov4k1n.tatarapp.ui.components.TheoryBlock
import com.dov4k1n.tatarapp.ui.theme.ListItemShape
import com.dov4k1n.tatarapp.ui.theme.TatarAppTheme

@Preview
@Composable
fun PreviewPhoneticsScreen() {
    TatarAppTheme(useDarkTheme = true) {
        PhoneticsScreen()
    }
}

@Composable
fun PhoneticsScreen(
    onPlayButtonClicked: (Int) -> Unit = {}
) {
    Column(
        modifier = Modifier.padding(bottom = 8.dp)
    ) {
        LazyColumn {
            item {
                TheoryBlock(
                    icon = BottomAppBarItems.Phonetics.selectedIcon,
                    heading = stringResource(R.string.bottom_bar_phonetics),
                    headingColor = colorScheme.onError,
                    subheading = stringResource(id = R.string.phonetics_subheading),
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                )
            }


            item {
                TheoryBlock(
                    icon = Icons.Outlined.MusicNote,
                    heading = stringResource(id = R.string.harmonies),
                    headingColor = colorScheme.onError,
                    subheading = stringResource(id = R.string.harmonies_subheading),
                    modifier = Modifier
                )
            }
            items(
                items = sectionHarmonies,
                key = { it.nameAddress }
            ) {
                when (it.id) {
                    1 -> {
                        NewLevelItem(
                            playArrowColor = colorScheme.onError,
                            sectionData = it,
                            onPlayButtonClicked = { onPlayButtonClicked(it.nameAddress) },
                            shape = ListItemShape.small,
                            modifier = Modifier
                                .padding(bottom = 2.dp)
                        )
                    }

                    sectionHarmonies.size -> {
                        NewLevelItem(
                            playArrowColor = colorScheme.onError,
                            sectionData = it,
                            onPlayButtonClicked = { onPlayButtonClicked(it.nameAddress) },
                            shape = ListItemShape.large
                        )
                    }

                    else -> {
                        NewLevelItem(
                            playArrowColor = colorScheme.onError,
                            sectionData = it,
                            onPlayButtonClicked = { onPlayButtonClicked(it.nameAddress) },
                            shape = ListItemShape.medium,
                            modifier = Modifier.padding(bottom = 2.dp)
                        )
                    }
                }
            }
        }
    Spacer(modifier = Modifier.weight(1f))
    }
}
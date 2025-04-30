package com.dov4k1n.tatarapp.ui.screens.phonetics

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MusicNote
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dov4k1n.tatarapp.R
import com.dov4k1n.tatarapp.data.BottomAppBarItem
import com.dov4k1n.tatarapp.data.sectionHarmonies
import com.dov4k1n.tatarapp.ui.components.ExpandableListItem
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
        modifier = Modifier.Companion.padding(bottom = 8.dp)
    ) {
        LazyColumn {
            item {
                TheoryBlock(
                    icon = BottomAppBarItem.Phonetics.selectedIcon,
                    heading = stringResource(R.string.bottom_bar_phonetics),
                    headingColor = MaterialTheme.colorScheme.onError,
                    subheading = stringResource(id = R.string.phonetics_subheading),
                    modifier = Modifier.Companion
                        .padding(bottom = 8.dp)
                )
            }


            item {
                TheoryBlock(
                    icon = Icons.Outlined.MusicNote,
                    heading = stringResource(id = R.string.harmonies),
                    headingColor = MaterialTheme.colorScheme.onError,
                    subheading = stringResource(id = R.string.harmonies_subheading),
                    modifier = Modifier.Companion
                )
            }
            items(
                items = sectionHarmonies,
                key = { it.nameAddress }
            ) {
                when (it.id) {
                    1 -> {
                        ExpandableListItem(
                            iconColor = MaterialTheme.colorScheme.onError,
                            sectionData = it,
                            onIconClick = { onPlayButtonClicked(it.nameAddress) },
                            shape = ListItemShape.small,
                            modifier = Modifier.Companion
                                .padding(bottom = 2.dp)
                        )
                    }

                    sectionHarmonies.size -> {
                        ExpandableListItem(
                            iconColor = MaterialTheme.colorScheme.onError,
                            sectionData = it,
                            onIconClick = { onPlayButtonClicked(it.nameAddress) },
                            shape = ListItemShape.large
                        )
                    }

                    else -> {
                        ExpandableListItem(
                            iconColor = MaterialTheme.colorScheme.onError,
                            sectionData = it,
                            onIconClick = { onPlayButtonClicked(it.nameAddress) },
                            shape = ListItemShape.medium,
                            modifier = Modifier.Companion.padding(bottom = 2.dp)
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.Companion.weight(1f))
    }
}
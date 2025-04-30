package com.dov4k1n.tatarapp.ui.screens.lexicon

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Fingerprint
import androidx.compose.material.icons.outlined.ModelTraining
import androidx.compose.material.icons.outlined.Style
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dov4k1n.tatarapp.R
import com.dov4k1n.tatarapp.data.Lexicon250
import com.dov4k1n.tatarapp.data.local.ThemeMode
import com.dov4k1n.tatarapp.data.sectionDictionary
import com.dov4k1n.tatarapp.ui.components.HeroBlock
import com.dov4k1n.tatarapp.ui.components.IntervalTrainingBlock
import com.dov4k1n.tatarapp.ui.components.NonExpandableListItem
import com.dov4k1n.tatarapp.ui.components.TheoryBlock
import com.dov4k1n.tatarapp.ui.theme.ListItemShape
import com.dov4k1n.tatarapp.ui.theme.TatarAppTheme

@Preview
@Composable
fun PreviewLexiconScreen() {
    TatarAppTheme(ThemeMode.Dark) {
        LexiconScreen()
    }
}

@Composable
fun LexiconScreen(
    modifier: Modifier = Modifier.Companion
) {
    LazyColumn {
        item {
            TheoryBlock(
                icon = Icons.Outlined.Fingerprint,
                heading = stringResource(id = R.string.lexicon),
                headingColor = MaterialTheme.colorScheme.inversePrimary,
                subheading = stringResource(id = R.string.lexicon_subheading),
                modifier = Modifier.Companion.padding(bottom = 8.dp)
            )
        }


        item {
            IntervalTrainingBlock(
                icon = Icons.Outlined.ModelTraining,
                heading = stringResource(id = R.string.training),
                headingColor = MaterialTheme.colorScheme.inversePrimary,
                subheading = stringResource(id = R.string.training_subheading),
                modifier = Modifier.Companion
            )
        }
        item {
            NonExpandableListItem(
                title = stringResource(id = R.string.cards),
                icon = Icons.Outlined.Style,
                iconColor = MaterialTheme.colorScheme.inversePrimary,
                onClick = { },
                shape = ListItemShape.small,
                modifier = Modifier.Companion
                    .padding(bottom = 2.dp)
            )
        }
        item {
            NonExpandableListItem(
                title = stringResource(id = R.string.matches),
                icon = Icons.Outlined.Dashboard,
                iconColor = MaterialTheme.colorScheme.inversePrimary,
                onClick = { },
                shape = ListItemShape.large,
                modifier = Modifier.Companion.padding(bottom = 8.dp)
            )
        }


        item {
            HeroBlock(
                icon = Icons.Outlined.Explore,
                heading = stringResource(id = R.string.dictionary),
                subheading = stringResource(id = R.string.explore_new_words),
                headingColor = MaterialTheme.colorScheme.inversePrimary,
                modifier = Modifier.Companion.clickable { /*TODO*/ }
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Outlined.Add,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.inversePrimary
                    )
                }
            }
        }
        items(
            items = sectionDictionary,
            key = { it.nameAddress }
        ) {
            when (it.id) {
                1 -> {
                    NonExpandableListItem(
                        title = stringResource(id = it.nameAddress),
                        subtitle = stringResource(id = it.moreDescription),
                        icon = it.icon,
                        iconColor = MaterialTheme.colorScheme.inversePrimary,
                        onClick = { },
                        shape = ListItemShape.small,
                        modifier = Modifier.Companion
                            .padding(bottom = 2.dp)
                    )
                }

                sectionDictionary.size -> {
                    NonExpandableListItem(
                        title = stringResource(id = it.nameAddress),
                        subtitle = stringResource(id = it.moreDescription),
                        icon = it.icon,
                        iconColor = MaterialTheme.colorScheme.inversePrimary,
                        onClick = { },
                        shape = ListItemShape.large,
                        modifier = Modifier.Companion.padding(bottom = 8.dp)
                    )
                }

                else -> {
                    NonExpandableListItem(
                        title = stringResource(id = it.nameAddress),
                        subtitle = stringResource(id = it.moreDescription),
                        icon = it.icon,
                        iconColor = MaterialTheme.colorScheme.inversePrimary,
                        onClick = { },
                        shape = ListItemShape.medium,
                        modifier = Modifier.Companion.padding(bottom = 2.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun OldLexiconScreen(

) {
    LazyColumn(
        modifier = Modifier.Companion.padding(vertical = 4.dp)
    ) {
        items(1) {
            Text(
                text = "Lexicon250",
                fontWeight = FontWeight.Companion.Bold,
                textAlign = TextAlign.Companion.Center
            )
        }
        items(
            items = Lexicon250,
            key = {
                it.id
            }
        ) {
            Row(
                modifier = Modifier.Companion.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Left Half
                Box(
                    modifier = Modifier.Companion
                        .weight(1f)
                        .padding(end = 4.dp)
                ) {
                    Text(
                        text = it.word,
                        textAlign = TextAlign.Companion.Start
                    )
                }

                // Right Half
                Box(
                    modifier = Modifier.Companion
                        .weight(1f)
                        .padding(start = 4.dp)
                ) {
                    Text(
                        text = it.ruTranslation,
                        textAlign = TextAlign.Companion.Start
                    )
                }
            }
        }
    }
}
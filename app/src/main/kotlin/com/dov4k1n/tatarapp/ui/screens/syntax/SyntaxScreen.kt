package com.dov4k1n.tatarapp.ui.screens.syntax

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dov4k1n.tatarapp.R
import com.dov4k1n.tatarapp.data.BottomAppBarItems
import com.dov4k1n.tatarapp.ui.components.TheoryBlock
import com.dov4k1n.tatarapp.ui.theme.TatarAppTheme

@Preview
@Composable
fun PreviewSyntaxScreen() {
    TatarAppTheme(useDarkTheme = true) {
        SyntaxScreen()
    }
}

@Composable
fun SyntaxScreen() {
    Column(
        modifier = Modifier.Companion.padding(bottom = 8.dp)
    ) {
        LazyColumn {
            item {
                TheoryBlock(
                    icon = BottomAppBarItems.Syntax.selectedIcon,
                    heading = stringResource(R.string.bottom_bar_syntax),
                    headingColor = MaterialTheme.colorScheme.errorContainer,
                    subheading = stringResource(id = R.string.syntax_subheading),
                    modifier = Modifier.Companion.padding(bottom = 8.dp)
                )
            }
        }
    }
}
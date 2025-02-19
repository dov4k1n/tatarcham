package com.dov4k1n.tatarapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dov4k1n.tatarapp.R
import com.dov4k1n.tatarapp.data.BottomAppBarItems
import com.dov4k1n.tatarapp.ui.components.TheoryBlock
import com.dov4k1n.tatarapp.ui.theme.TatarAppTheme

@Preview
@Composable
fun PreviewCultureScreen() {
    TatarAppTheme(useDarkTheme = true) {
        CultureScreen()
    }
}

@Composable
fun CultureScreen() {
    Column(
        modifier = Modifier.padding(bottom = 8.dp)
    ) {
        LazyColumn {
            item {
                Image(
                    painter = painterResource(R.drawable.golden_horde),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp, bottomStart = 8.dp, bottomEnd = 8.dp))
                )
            }
            item {
                TheoryBlock(
                    icon = BottomAppBarItems.Culture.selectedIcon,
                    heading = stringResource(R.string.bottom_bar_culture),
                    headingColor = colorScheme.onErrorContainer,
                    subheading = stringResource(id = R.string.culture_subheading),
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                )
            }
        }
    }
}
package com.dov4k1n.tatarapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Architecture
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dov4k1n.tatarapp.R
import com.dov4k1n.tatarapp.ui.components.SupportDevelopersButton
import com.dov4k1n.tatarapp.ui.theme.TatarAppTheme

@Preview
@Composable
fun PreviewUnderDevelopmentScreen() {
    TatarAppTheme(useDarkTheme = true) {
        UnderDevelopmentScreen("screen name")
    }
}

@Composable
fun UnderDevelopmentScreen(
    name: String
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1.2f))
        Icon(
            imageVector = Icons.Outlined.Architecture,
            contentDescription = null,
            tint = colorScheme.onSurfaceVariant,
            modifier = Modifier.size(160.dp)
        )
        Text(
            text = name,
            style = typography.headlineSmall,
            color = colorScheme.primary,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = stringResource(R.string.under_development),
            style = typography.bodyMedium,
            color = colorScheme.onBackground
        )
        Spacer(modifier = Modifier.weight(1f))
        SupportDevelopersButton(
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}
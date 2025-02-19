package com.dov4k1n.tatarapp.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dov4k1n.tatarapp.R
import com.dov4k1n.tatarapp.ui.theme.TatarAppTheme

@Preview
@Composable
fun PreviewSupportDevelopersButton() {
    TatarAppTheme(useDarkTheme = true) {
        SupportDevelopersButton()
    }
}

@Composable
fun SupportDevelopersButton(
    modifier: Modifier = Modifier
) {

    var openDonationDialog by remember { mutableStateOf(false) }
    if (openDonationDialog) {
        OpenLinkDialog(
            link = stringResource(R.string.donation_link),
            title = stringResource(R.string.open_donation_page),
            onDismissRequest = { openDonationDialog = false },
        )
    }

    ElevatedButton(
        onClick = { openDonationDialog = true },
        colors = ButtonColors(
            contentColor = colorScheme.onError,
            containerColor = colorScheme.primaryContainer,
            disabledContentColor = colorScheme.onError,
            disabledContainerColor = colorScheme.onError
        ),
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = null,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = stringResource(R.string.support_developers),
                style = typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
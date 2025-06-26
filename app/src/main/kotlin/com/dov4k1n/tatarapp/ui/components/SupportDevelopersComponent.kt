/*
 * Tatarcham v0.2, tatar language learning app for Android.
 * Copyright (C) 2023-2025 Ayzat Rizatdinov <ddov4k1n at gmail dot com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see https://www.gnu.org/licenses/.
 *
 */

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
        modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp)
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
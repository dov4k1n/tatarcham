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
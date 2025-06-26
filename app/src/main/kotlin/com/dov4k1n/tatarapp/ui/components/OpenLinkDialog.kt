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

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.dov4k1n.tatarapp.R
import com.dov4k1n.tatarapp.ui.theme.TatarAppTheme

@Preview
@Composable
fun PreviewOpenLinkDialog() {
    TatarAppTheme(useDarkTheme = true) {
        OpenLinkDialog(
            title = "Перейти на страницу пожертвований",
            link = "https://google.com",
            onDismissRequest = {},
        )
    }
}

@Composable
fun OpenLinkDialog(
    title: String,
    link: String,
    onDismissRequest: () -> Unit,
) {
    val context = LocalContext.current
    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Surface(
            color = colorScheme.primaryContainer,
            shape = shapes.medium,
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = title,
                    style = typography.titleLarge,
                    color = colorScheme.primary,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    text = stringResource(R.string.open_link_question),
                    style = typography.bodyMedium,
                    color = colorScheme.primary,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = link,
                    style = typography.bodyMedium,
                    color = colorScheme.onError,
                )
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 4.dp),
                ) {
                    Card(
                        shape = shapes.medium,
                        colors = CardColors(
                            containerColor = Color.Transparent,
                            contentColor = colorScheme.onError,
                            disabledContainerColor = Color.Transparent,
                            disabledContentColor = colorScheme.onError
                        ),
                        modifier = Modifier
                            .padding(end = 24.dp)
                            .clickable { onDismissRequest() }
                    ) {
                        Text(
                            text = stringResource(R.string.cancel),
                            style = typography.bodyMedium,
                            color = colorScheme.onError,
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                    Card(
                        shape = shapes.medium,
                        colors = CardColors(
                            containerColor = Color.Transparent,
                            contentColor = colorScheme.onError,
                            disabledContainerColor = Color.Transparent,
                            disabledContentColor = colorScheme.onError
                        ),
                        modifier = Modifier
                            .clickable {
                                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link)))
                            }
                    ) {
                        Text(
                            text = stringResource(R.string.open),
                            style = typography.bodyMedium,
                            color = colorScheme.onError,
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }
            }
        }
    }
}
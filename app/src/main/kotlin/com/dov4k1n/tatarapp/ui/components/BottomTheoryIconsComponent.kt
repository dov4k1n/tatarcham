package com.dov4k1n.tatarapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.HelpCenter
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BottomTheoryIcons() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp, vertical = 10.dp)
    ) {
        Icon(
            imageVector = Icons.Outlined.GridView,
            contentDescription = "grid view",
            tint = colorScheme.onBackground,
            modifier = Modifier.size(24.dp)
        )
        Icon(
            imageVector = Icons.AutoMirrored.Outlined.MenuBook,
            contentDescription = "grid view",
            tint = colorScheme.primary,
            modifier = Modifier.size(40.dp)
        )
        Icon(
            imageVector = Icons.AutoMirrored.Outlined.HelpCenter,
            contentDescription = "grid view",
            tint = colorScheme.onBackground,
            modifier = Modifier.size(24.dp)
        )
    }
}
package com.dov4k1n.tatarapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dov4k1n.tatarapp.ui.theme.ListItemShape

@Preview
@Composable
fun RowAlign() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(horizontal = 8.dp)
    ) {
        Cards(
            title = "Настоящее время",
            shape = ListItemShape.small,
            modifier = Modifier
                .padding(bottom = 2.dp)
        )
        Cards(
            title = "Определённое прошедшее время",
            shape = ListItemShape.medium,
            modifier = Modifier
                .padding(bottom = 2.dp)
        )
        Cards(
            title = "Неопределённое прошедшее время время время время время время время время время время время время время время время время время время",
            shape = ListItemShape.large
        )
    }
}

@Composable
fun Cards(
    title: String,
    shape: CornerBasedShape,
    modifier: Modifier = Modifier
) {
    Card(
        shape = shape,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(color = Color(0xFF202F36))
        ) {
            IconButton(
                onClick = { }
            ) {
                Icon(
                    imageVector = Icons.Outlined.PlayArrow,
                    contentDescription = null,
                    tint = Color(0xFFFFFFFF)
                )
            }
            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFFFFFFFF),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(
                        start = 0.dp,
                        end = 8.dp
                    )
                    .weight(1f)
            )
            IconButton(
                onClick = {},
            ) {
                Icon(
                    imageVector = Icons.Filled.ExpandMore,
                    contentDescription = null,
                    tint = Color(0xFFFFFFFF)
                )
            }
        }
    }
}
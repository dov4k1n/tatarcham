package com.dov4k1n.tatarapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dov4k1n.tatarapp.R
import com.dov4k1n.tatarapp.data.MorphologyScreen
import com.dov4k1n.tatarapp.data.bottomAppBarTabsList
import com.dov4k1n.tatarapp.ui.theme.AppIconShape
import com.dov4k1n.tatarapp.ui.theme.TatarAppTheme

@Preview
@Composable
fun PreviewTopAppBar() {
    TatarAppTheme(useDarkTheme = false) {
        TopAppBar(navController = NavHostController(LocalContext.current))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onNavigationDrawerIconClick: () -> Unit = {},
    onLanguageIconClick: () -> Unit = {}
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val cantNavigateBack: Boolean = bottomAppBarTabsList.any {
        it.route == currentDestination?.route || currentDestination?.route == MorphologyScreen.Morphology.name
    }

    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 4.dp)
            ) {
//                Image(
//                    modifier = Modifier
//                        .size(40.dp)
//                        .padding(horizontal = dimensionResource(id = R.dimen.padding_small_4dp))
//                        .clip(AppIconShape.small),
//                    painter = painterResource(id = R.drawable.tg_app_logo),
//                    contentDescription = null
//                )
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = typography.headlineLarge,
                )
            }
        },
        navigationIcon = {
            if (cantNavigateBack) {
                IconButton(
                    onClick = onNavigationDrawerIconClick
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Menu,
                        contentDescription = null,
                    )
                }
            } else {
                IconButton(
                   onClick = {
                       navController.navigateUp()
                   }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                    )
                }
            }
        },
        actions = {
            IconButton(
                onClick = onLanguageIconClick
            ) {
                Icon(
                    imageVector = Icons.Outlined.Language,
                    contentDescription = null,
                )
            }
        },
        colors = TopAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = Color.Transparent,
            navigationIconContentColor = colorScheme.onBackground,
            titleContentColor = colorScheme.primary,
            actionIconContentColor = colorScheme.onBackground
        ),
    )
}
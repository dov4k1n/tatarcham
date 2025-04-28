package com.dov4k1n.tatarapp.ui.screens

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BugReport
import androidx.compose.material.icons.outlined.Code
import androidx.compose.material.icons.outlined.Devices
import androidx.compose.material.icons.outlined.Forum
import androidx.compose.material.icons.outlined.IosShare
import androidx.compose.material.icons.outlined.Policy
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dov4k1n.tatarapp.R
import com.dov4k1n.tatarapp.data.local.ThemeMode
import com.dov4k1n.tatarapp.ui.components.NonExpandableListItem
import com.dov4k1n.tatarapp.ui.components.OpenLinkDialog
import com.dov4k1n.tatarapp.ui.components.SupportDevelopersButton
import com.dov4k1n.tatarapp.ui.theme.ListItemShape
import com.dov4k1n.tatarapp.ui.theme.Shapes
import com.dov4k1n.tatarapp.ui.theme.TatarAppTheme

@Preview
@Composable
fun PreviewAboutAppScreen() {
    TatarAppTheme(themeMode = ThemeMode.Dark) {
        AboutAppScreen()
    }
}

@Composable
fun AboutAppScreen() {

    val iconsColor = colorScheme.onBackground

    var openSourceCodeDialog by remember { mutableStateOf(false) }
    if (openSourceCodeDialog) {
        OpenLinkDialog(
            title = stringResource(R.string.open_source_code),
            link = stringResource(R.string.source_code_link),
            onDismissRequest = { openSourceCodeDialog = false }
        )
    }

    var openBugReportDialog by remember { mutableStateOf(false) }
    if (openBugReportDialog) {
        OpenLinkDialog(
            title = stringResource(R.string.open_github_issues),
            link = stringResource(R.string.report_bug_or_suggest_improvements_link),
            onDismissRequest = { openBugReportDialog = false }
        )
    }

    var openPrivacyPolicyDialog by remember { mutableStateOf(false) }
    if (openPrivacyPolicyDialog) {
        OpenLinkDialog(
            title = stringResource(R.string.open_privacy_policy),
            link = stringResource(R.string.privacy_policy_link),
            onDismissRequest = { openPrivacyPolicyDialog = false }
        )
    }

    var openWebsiteDialog by remember { mutableStateOf(false) }
    if (openWebsiteDialog) {
        OpenLinkDialog(
            title = stringResource(R.string.open_website),
            link = stringResource(R.string.website_link),
            onDismissRequest = { openWebsiteDialog = false }
        )
    }

    var openTelegramChannelDialog by remember { mutableStateOf(false) }
    if (openTelegramChannelDialog) {
        OpenLinkDialog(
            title = stringResource(R.string.open_telegram_channel),
            link = stringResource(R.string.telegram_channel_link),
            onDismissRequest = { openTelegramChannelDialog = false }
        )
    }

    val inviteFriendsIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, stringResource(R.string.share_app_message))
    }

    val context = LocalContext.current
    val packageInfo = context.packageManager
        .getPackageInfo(context.packageName, 0)
//    val version = remember(packageInfo) { packageInfo.versionName }
    val version = "0.2.0"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(
            shape = Shapes.large,
            border = BorderStroke(
                width = 2.dp,
                color = colorScheme.primaryContainer
            ),
            colors = CardColors(
                containerColor = Color.Transparent,
                contentColor = colorScheme.onBackground,
                disabledContainerColor = Color(0xFFFFFFFF),
                disabledContentColor = Color(0xFFFFFFFF)
            ),
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ){
            Text(
                text = stringResource(R.string.app_description),
                style = typography.bodyMedium,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            )
        }

        Text(
            text = stringResource(R.string.version) + " " + version,
            style = typography.bodyMedium,
            color = colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        NonExpandableListItem(
            title = stringResource(R.string.source_code),
            subtitle = stringResource(R.string.source_code_license),
            icon = Icons.Outlined.Code,
            iconColor = iconsColor,
            shape = ListItemShape.small,
            onClick = {
                openSourceCodeDialog = true
            },
            modifier = Modifier
                .padding(bottom = 2.dp)
        )
        NonExpandableListItem(
            title = stringResource(R.string.report_bug_or_suggest_improvements),
            icon = Icons.Outlined.BugReport,
            iconColor = iconsColor,
            shape = ListItemShape.medium,
            onClick = {
                openBugReportDialog = true
            },
            modifier = Modifier
                .padding(bottom = 2.dp)
        )
        NonExpandableListItem(
            title = stringResource(R.string.privacy_policy),
            icon = Icons.Outlined.Policy,
            iconColor = iconsColor,
            shape = ListItemShape.medium,
            onClick = {
                openPrivacyPolicyDialog = true
            },
            modifier = Modifier
                .padding(bottom = 2.dp)
        )
        NonExpandableListItem(
            title = stringResource(R.string.web),
            icon = Icons.Outlined.Devices,
            iconColor = iconsColor,
            shape = ListItemShape.medium,
            onClick = {
                openWebsiteDialog = true
            },
            modifier = Modifier
                .padding(bottom = 2.dp)
        )
        NonExpandableListItem(
            title = stringResource(R.string.community),
            icon = Icons.Outlined.Forum,
            iconColor = iconsColor,
            shape = ListItemShape.medium,
            onClick = {
                openTelegramChannelDialog = true
            },
            modifier = Modifier
                .padding(bottom = 2.dp)
        )
        NonExpandableListItem(
            title = stringResource(R.string.share_app),
            icon = Icons.Outlined.IosShare,
            iconColor = iconsColor,
            shape = ListItemShape.large,
            onClick = {
                context.startActivity(
                    Intent.createChooser(
                        inviteFriendsIntent,
                        context.getString(R.string.share_app)
                    )
                )
            },
            modifier = Modifier
                .padding(bottom = 8.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        SupportDevelopersButton(
            modifier = Modifier
                .padding(bottom = 8.dp)
        )
    }
}

package com.example.visionbook.view.settingsScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.visionbook.R
import com.example.visionbook.data.NotificationSettingsItem
import com.example.visionbook.models.AutoresizedText
import com.example.visionbook.view.camerasBookNProfile.itemsInCameras.BackButton

@Composable
fun NotificationScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 12.dp, end = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

            BackButton(navController = navController)
        Text(stringResource(R.string.notset_title), style = MaterialTheme.typography.headlineLarge, textAlign = TextAlign.Center, modifier = Modifier.padding(top = 20.dp))

        val notificationSettingsItems = listOf (
            NotificationSettingsItem(stringResource(R.string.notset_comm), stringResource(R.string.theme)),
            NotificationSettingsItem(stringResource(R.string.notset_mention), stringResource(R.string.profile_settings)),
            NotificationSettingsItem(stringResource(R.string.notset_newsub), stringResource(R.string.notification)),
            NotificationSettingsItem(stringResource(R.string.notset_recom), stringResource(R.string.security)),
            NotificationSettingsItem(stringResource(R.string.notset_interestpost), stringResource(R.string.language)),
            NotificationSettingsItem(stringResource(R.string.notset_interestgenre), stringResource(R.string.faq)),
            NotificationSettingsItem(stringResource(R.string.notset_populargenre), stringResource(R.string.faq)),
        )
        LazyColumn(
            Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)) {
            items(notificationSettingsItems) {item ->
                NotificationSettingsButton(contentDescription = item.contentDescription, text = item.text)
            }
        }
    }
}
@Composable
fun NotificationSettingsButton(contentDescription: String, text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,

        ) {
        AutoresizedText(
            text = contentDescription,
            modifier = Modifier.padding(start = 20.dp),
            style = MaterialTheme.typography.headlineMedium
        )
        IconButton(onClick = { /*TODO*/ }) {
            Icon(painter = painterResource(com.google.android.exoplayer2.R.drawable.exo_ic_chevron_right), contentDescription = "right arrow")
        }
    }

}

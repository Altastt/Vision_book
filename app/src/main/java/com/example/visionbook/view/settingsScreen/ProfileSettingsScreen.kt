package com.example.visionbook.view.settingsScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.visionbook.R
import com.example.visionbook.data.ProfileSettingsItem
import com.example.visionbook.models.AutoresizedText
import com.example.visionbook.models.NavigationItems
import com.example.visionbook.view.camerasBookNProfile.itemsInCameras.BackButton
import com.example.visionbook.view.navigation.AuthScreen

@Composable
fun ProfileSettingsScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 12.dp, end = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BackButton(navController = navController)
        Text(
            "Настройки профиля",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(top = 20.dp)
        )
        Image(
            painter = painterResource(R.drawable.profile),
            contentDescription = "Null Avatar",
            modifier = Modifier
                .size(200.dp)
                .padding(top = 20.dp),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
        )
        val profileSettingstItems = listOf(
            ProfileSettingsItem("Сменить пользователя", stringResource(R.string.theme)),
            ProfileSettingsItem("О себе", stringResource(R.string.profile_settings)),
            ProfileSettingsItem("Дата рождения", stringResource(R.string.notification)),
            ProfileSettingsItem("Страна/город", stringResource(R.string.security)),
            ProfileSettingsItem("Адресс эл. почты", stringResource(R.string.language)),
            ProfileSettingsItem("Пароль", stringResource(R.string.faq)),
            ProfileSettingsItem("Выход из профиля", stringResource(R.string.faq)),
        )
        LazyColumn(
            Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
        ) {
            items(profileSettingstItems) { item ->
                ProfileSettingsButton(
                    contentDescription = item.contentDescription, text = item.text, navController
                )
            }
        }
    }
}

@Composable
fun ProfileSettingsButton(contentDescription: String, text: String, navController: NavController) {
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
        IconButton(onClick = {
            if (contentDescription == "Выход из профиля") {
                navController.navigate(AuthScreen.Login.route)
            } else {
                TODO()
            }
        }
        ) {
            Icon(
                painter = painterResource(com.google.android.exoplayer2.R.drawable.exo_ic_chevron_right),
                contentDescription = "right arrow"
            )
        }
    }

}
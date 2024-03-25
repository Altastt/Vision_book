package com.example.myapplication.mainScreens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.models.NavigationItems
import com.example.myapplication.secondScreens.PostElement

@Composable
fun ProfileScreen(navController: NavController, onThemeUpdated: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 22.dp, end = 22.dp),
    ) {
            Button(onClick = {},
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 20.dp),
                elevation = ButtonDefaults.elevatedButtonElevation(0.dp),
                shape = RoundedCornerShape(30.dp),
            ) {
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(model = PostElement.avatar, "Avatar",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, start = 15.dp)
                            .size(65.dp)
                            .clip(CircleShape))

                    Text(
                        PostElement.nickname,
                        modifier = Modifier.padding(start = 50.dp),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }

            IconButton(onClick = { navController.navigate(NavigationItems.CameraForProfile.route) },
                modifier = Modifier.padding(end = 15.dp)
                    .size(35.dp)
                    ) {
                Icon(
                    painter = painterResource(R.drawable.add_photo),
                    "Add_photo",
                )
            }



        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(top = 44.dp)
                .clip(RoundedCornerShape(15))
        ) {
            val menuItems = listOf(
                MenuItem(R.drawable.theme, "Theme", stringResource(R.string.theme)),
                MenuItem(R.drawable.profile_settings, "Profile Settings", stringResource(R.string.profile_settings)),
                MenuItem(R.drawable.notification, "Notification", stringResource(R.string.notification)),
                MenuItem(R.drawable.safety, "Security", stringResource(R.string.security)),
                MenuItem(R.drawable.language, "Language", stringResource(R.string.language)),
                MenuItem(R.drawable.faq, "FAQ", stringResource(R.string.faq)),
                MenuItem(R.drawable.quit, "Quit profile", stringResource(R.string.exit_profile))
            )

            menuItems.forEach { menuItem ->
                MenuButton(
                    iconId = menuItem.iconId,
                    contentDescription = menuItem.contentDescription,
                    text = menuItem.text,
                    onThemeUpdated
                )
            }
        }
    }
}
@Composable
fun MenuButton(iconId: Int, contentDescription: String, text: String, onThemeUpdated: () -> Unit) {
    Button(
        onClick = {
                  when (contentDescription) {
                      "Theme" -> {
                            onThemeUpdated()
                      }
                      "Profile Settings" -> {}
                      "Notification" -> {}
                      "Security" -> {}
                      "Language" -> {}
                      "FAQ" -> {}
                      "Quit profile" -> {}
                  }
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(0)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(iconId),
                contentDescription = contentDescription,
                modifier = Modifier.padding(start = 25.dp).size(35.dp)
            )
            Text(
                text = text,
                modifier = Modifier.padding(start = 20.dp),
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}

data class MenuItem(val iconId: Int, val contentDescription: String, val text: String)

package com.example.myapplication.mainScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.models.NavigationItems
import com.example.myapplication.secondScreens.PostElement
import com.example.myapplication.ui.theme.DarkGrey
import com.example.myapplication.ui.theme.LightGreyText
import com.example.myapplication.ui.theme.Orange
import com.example.myapplication.ui.theme.sourceSans

@Composable
fun ProfileScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 22.dp, end = 22.dp),
    ) {
        Box(modifier = Modifier.height(150.dp),
            contentAlignment = Alignment.BottomEnd) {
            Row(modifier = Modifier.fillMaxWidth()
                .padding(bottom = 30.dp)
                .clip(shape = RoundedCornerShape(30))
                .background(color = MaterialTheme.colorScheme.secondary),
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
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontFamily = sourceSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    )
                )
            }

            IconButton(onClick = { navController.navigate(NavigationItems.CameraForProfile.route) },
                modifier = Modifier.padding(end = 15.dp)
                    .clip(shape = RoundedCornerShape(30))
                    .size(50.dp)
                    .background(MaterialTheme.colorScheme.primary),) {
                Icon(
                    painter = painterResource(R.drawable.add_photo),
                    "Add_photo",
                    modifier = Modifier.size(40.dp),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }

        }

        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(top = 44.dp)
                .clip(RoundedCornerShape(15))
                .background(MaterialTheme.colorScheme.primary),
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
                    text = menuItem.text
                )
            }
        }
    }
}
@Composable
fun MenuButton(iconId: Int, contentDescription: String, text: String) {
    Button(
        onClick = {},
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colorScheme.primary),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(iconId),
                contentDescription = contentDescription,
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(start = 25.dp).size(35.dp)
            )
            Text(
                text = text,
                modifier = Modifier.padding(start = 20.dp),
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontFamily = sourceSans,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )
            )
        }
    }
}

data class MenuItem(val iconId: Int, val contentDescription: String, val text: String)

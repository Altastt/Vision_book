package com.example.myapplication.view.profileScreens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.models.AutoresizedText
import com.example.myapplication.view.camerasBookNProfile.itemsInCameras.BackButton
import com.example.myapplication.viewmodels.ProfileScreenVM

@Composable
fun ProfileScreen(navController: NavController, viewModel: ProfileScreenVM = viewModel()) {
    val profileList = viewModel.profileList.value
    val firstProfile = profileList.firstOrNull()

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            modifier = Modifier.fillMaxWidth().padding(bottom = 60.dp)
        ) {
            BackButton(navController = navController)
        }
        AsyncImage(model = firstProfile?.url, "Avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(180.dp).clip(CircleShape))
        Spacer(modifier = Modifier.height(20.dp))
        firstProfile?.nickname?.let {
            AutoresizedText(
                it,
                style = MaterialTheme.typography.labelMedium)
        }
        Row (
            modifier = Modifier.padding(top = 50.dp, bottom = 20.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(
                onClick = {},
                modifier = Modifier.size(45.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.report),
                    "Report",
                    modifier = Modifier.size(35.dp)
                )
            }
            Button(
                onClick = {},
            ) {
                AutoresizedText(
                    stringResource(R.string.user_subs_bt),
                    style = MaterialTheme.typography.labelMedium
                )
            }
            IconButton(
                onClick = {},
                modifier = Modifier.size(45.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.share),
                    "Share",
                    modifier = Modifier.size(35.dp)
                )
            }
        }
        Row (
            modifier = Modifier.fillMaxWidth().padding(top = 30.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                AutoresizedText(
                    firstProfile?.followersCount.toString(),
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(5.dp))
                AutoresizedText(
                    stringResource(R.string.user_subscribers),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                AutoresizedText(
                    firstProfile?.followingCount.toString(),
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(5.dp))
                AutoresizedText(
                    stringResource(R.string.user_subscribe),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

    }
}
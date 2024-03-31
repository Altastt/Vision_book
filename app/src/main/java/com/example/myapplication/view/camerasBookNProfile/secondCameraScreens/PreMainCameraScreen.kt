package com.example.myapplication.view.camerasBookNProfile.secondCameraScreens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.models.AutoresizedText
import com.example.myapplication.models.NavigationItems
import com.example.myapplication.view.camerasBookNProfile.itemsInCameras.TextFieldCustom

@Composable
fun PreMainCameraScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(top = 30.dp, start = 22.dp, end = 22.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var showAdditionalFields by remember { mutableStateOf(false) }

        AutoresizedText(
            modifier = Modifier.padding(bottom = 10.dp),
            text = stringResource(R.string.premaincamera_newbook),
            style = MaterialTheme.typography.headlineLarge
        )

        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // dismiss
            Button(
                onClick = { navController.navigate(NavigationItems.Books.route) }
            ) {
                AutoresizedText(
                    text = stringResource(R.string.premaincamera_newbook_choose),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            // confirm
            Button(
                onClick = {
                    showAdditionalFields = true
                }
            ) {
                AutoresizedText(
                    text = stringResource(R.string.premaincamera_newbook_bt),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
        if (showAdditionalFields) {
            Column(
                modifier = Modifier.padding(top = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextFieldCustom(stringResource(R.string.name))
                Spacer(modifier = Modifier.padding(top = 10.dp))
                TextFieldCustom(stringResource(R.string.author))
                Spacer(modifier = Modifier.padding(top = 10.dp))
                TextFieldCustom(stringResource(R.string.genre))

                Button(
                    modifier = Modifier.padding(top = 20.dp),
                    onClick = {
                        navController.navigate(NavigationItems.CameraForProfile.route)
                        showAdditionalFields = false
                    }
                ) {
                    AutoresizedText(
                        text = stringResource(R.string.premaincamera_newbook_continue),
                        style = MaterialTheme.typography.titleMedium
                    )
                }

            }
        }
    }
}
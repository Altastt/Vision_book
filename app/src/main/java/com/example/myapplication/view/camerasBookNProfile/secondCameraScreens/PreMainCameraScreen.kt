package com.example.myapplication.view.camerasBookNProfile.secondCameraScreens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.view.camerasBookNProfile.itemsInCameras.TextFieldCustom
import com.example.myapplication.models.NavigationItems

@Composable
fun PreMainCameraScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(top = 30.dp, start = 22.dp, end = 22.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var showAdditionalFields by remember { mutableStateOf(false) }

        Text(
            modifier = Modifier.padding(bottom = 10.dp),
            text = stringResource(R.string.premaincamera_newbook),
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            text = stringResource(R.string.premaincamera_newbook_text),
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // dismiss
            Button(
                onClick = { navController.navigate(NavigationItems.Books.route) }
            ) {
                Text(
                    text = stringResource(R.string.premaincamera_newbook_no),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            // confirm
            Button(
                onClick = {
                    showAdditionalFields = true
                }
            ) {
                Text(
                    text = stringResource(R.string.premaincamera_newbook_yes),
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
                    Text(
                        text = stringResource(R.string.premaincamera_newbook_continue),
                        style = MaterialTheme.typography.titleMedium
                    )
                }

            }
        }
    }
}
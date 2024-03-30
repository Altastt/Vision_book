package com.example.myapplication.view.camerasBookNProfile.itemsInCameras

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.R

@Composable
fun BackButton(navController: NavController) {
    Row (modifier = Modifier.fillMaxWidth().padding(start = 12.dp, top = 15.dp),
        horizontalArrangement = Arrangement.Start) {
        IconButton(
            onClick = {
                navController.navigateUp()
            },
            modifier = Modifier
                .size(45.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.arrow_back),
                contentDescription = "Back Arrow",
            )
        }
    }
}
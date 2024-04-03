package com.example.myapplication.view.authScreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.models.AutoresizedText
import com.example.myapplication.view.camerasBookNProfile.itemsInCameras.BackButton
import com.example.myapplication.view.camerasBookNProfile.itemsInCameras.TextFieldCustom
import com.example.myapplication.view.navigation.AuthScreen


@Composable
fun ForgotScreen(navController: NavController) {
    Column (
        modifier = Modifier.fillMaxSize().padding(start = 12.dp, end = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.padding(bottom = 80.dp)
        ) { BackButton(navController) }

        AutoresizedText(stringResource(R.string.forgot_title),
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.padding(bottom = 120.dp))

        Text(stringResource(R.string.forgot_text),
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding( bottom = 16.dp),
            textAlign = TextAlign.Center
            )

        TextFieldCustom(stringResource(R.string.sign_in_email))

        Button(
            onClick = { // забывать про предыдущий экран
                navController.navigate(AuthScreen.Login.route) },
            modifier = Modifier.padding(top = 60.dp),
            shape = RoundedCornerShape(30)
            ) {
            AutoresizedText(stringResource(R.string.forgot_button),
                style = MaterialTheme.typography.labelMedium)
        }
    }

}
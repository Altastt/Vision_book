package com.example.visionbook.view.authScreens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.visionbook.R
import com.example.visionbook.models.AutoresizedText
import com.example.visionbook.view.camerasBookNProfile.itemsInCameras.BackButton
import com.example.visionbook.view.camerasBookNProfile.itemsInCameras.TextFieldCustom
import com.example.visionbook.view.camerasBookNProfile.itemsInCameras.TextFieldEmail
import com.example.visionbook.view.navigation.AuthScreen
import com.example.visionbook.viewmodels.AuthVM


@Composable
fun ForgotScreen(navController: NavController, authViewModel: AuthVM) {
    val context = LocalContext.current
    val emailState = remember { mutableStateOf("") }
    val checkEmail = stringResource(R.string.check_email)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 12.dp, end = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.padding(bottom = 80.dp)
        ) { BackButton(navController) }

        AutoresizedText(
            stringResource(R.string.forgot_title),
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.padding(bottom = 120.dp)
        )

        Text(
            stringResource(R.string.forgot_text),
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(bottom = 16.dp),
            textAlign = TextAlign.Center
        )

        TextFieldEmail(
            stringResource(R.string.sign_in_email),
            emailState,
            onValueChange = { newValue -> emailState.value = newValue })

        Button(
            onClick = {
                if (emailState.value != "") {
                    navController.navigate(AuthScreen.Login.route) {
                        navController.popBackStack(AuthScreen.Login.route, true)
                    }
                } else {
                    Toast.makeText(
                        context,
                        checkEmail,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            modifier = Modifier.padding(top = 60.dp),
            shape = RoundedCornerShape(30)
        ) {
            AutoresizedText(
                stringResource(R.string.forgot_button),
                style = MaterialTheme.typography.labelMedium
            )
        }
    }

}
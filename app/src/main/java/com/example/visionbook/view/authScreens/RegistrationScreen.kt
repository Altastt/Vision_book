package com.example.visionbook.view.authScreens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.visionbook.R
import com.example.visionbook.models.AutoresizedText
import com.example.visionbook.models.api.AuthApi
import com.example.visionbook.view.camerasBookNProfile.itemsInCameras.BackButton
import com.example.visionbook.view.camerasBookNProfile.itemsInCameras.TextFieldCustom
import com.example.visionbook.view.camerasBookNProfile.itemsInCameras.TextFieldPass
import com.example.visionbook.view.navigation.GraphRoute
import com.example.visionbook.viewmodels.AuthVM
import com.example.visionbook.viewmodels.RetrofitVM
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Thread.sleep


@Composable
fun RegistrationScreen(
    navController: NavController,
    authViewModel: AuthVM = viewModel(),
    retrofitViewModel: RetrofitVM = viewModel()
) {
    val authApi = retrofitViewModel.retrofit.create(AuthApi::class.java)
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    // Забираем значения из вьюмодели
    DisposableEffect(authViewModel) {

        val observerEmailState = Observer<String> { _emailState ->
            emailState.value = _emailState
        }
        authViewModel.emailState.observeForever(observerEmailState)

        val observerPasswordState = Observer<String> { _passwordState ->
            passwordState.value = _passwordState
        }
        authViewModel.passwordState.observeForever(observerPasswordState)
        onDispose {
            authViewModel.emailState.removeObserver(observerEmailState)
            authViewModel.passwordState.removeObserver(observerPasswordState)
        }
    }
    var checked by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.padding(start = 12.dp, end = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 80.dp)
        ) { BackButton(navController) }

        AutoresizedText(
            stringResource(R.string.sign_up_title),
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.padding(bottom = 110.dp)
        )

        TextFieldCustom(
            stringResource(R.string.sign_in_email),
            emailState,
            onValueChange = { newValue -> emailState.value = newValue })
        Spacer(modifier = Modifier.height(15.dp))
        // Передать сюда состояния с вьюмодели для пароля
        TextFieldPass(
            stringResource(R.string.sign_in_password),
            passwordState = passwordState,
            onValueChange = { newValue -> passwordState.value = newValue }
        )
        Spacer(modifier = Modifier.height(15.dp))
        TextFieldPass(
            stringResource(R.string.sign_in_retrypassword),
            secondPassword = true,
            passwordToMatch = passwordState.value,
            passwordState,
            onValueChange = { newValue -> passwordState.value = newValue }
        )


        Row(
            modifier = Modifier.padding(start = 30.dp, top = 30.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = { _checked ->
                    checked = _checked
                },
                modifier = Modifier.size(50.dp).padding(end = 10.dp)
            )
            Text(
                stringResource(R.string.sign_up_confidence),
                style = MaterialTheme.typography.titleSmall
            )
        }
        // ассинхронщина тут
        Button(
            onClick = { // ПОФИКСИТЬ БЭКСТЭК, РАЗДЕЛИТЬ КОРУТИНЫ
                CoroutineScope(Dispatchers.IO).launch {
                    authViewModel.registration(emailState.value, passwordState.value, authApi)
                    sleep(1000)
                    authViewModel.authorization(emailState.value, passwordState.value, authApi)
                }
                navController.navigate(GraphRoute.MAIN) {
                    popUpTo(GraphRoute.MAIN)
                }
            },
            shape = RoundedCornerShape(30),
            modifier = Modifier.width(140.dp).height(80.dp).padding(top = 30.dp),

            ) {
            AutoresizedText(
                stringResource(R.string.sign_in_tb_to_signup),
                style = MaterialTheme.typography.labelMedium
            )
        }

    }


}
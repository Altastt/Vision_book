package com.example.visionbook.view.authScreens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.visionbook.R
import com.example.visionbook.models.AutoresizedText
import com.example.visionbook.models.api.AuthApi
import com.example.visionbook.view.camerasBookNProfile.itemsInCameras.BackButton
import com.example.visionbook.view.camerasBookNProfile.itemsInCameras.TextFieldEmail
import com.example.visionbook.view.camerasBookNProfile.itemsInCameras.TextFieldPass
import com.example.visionbook.view.navigation.AuthScreen
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
    authViewModel: AuthVM,
    retrofitViewModel: RetrofitVM = viewModel()
) {
    val context = LocalContext.current
    val authApi = retrofitViewModel.retrofit.create(AuthApi::class.java)
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val secondPasswordState = remember { mutableStateOf("") }
    var passwordsMatchState by remember { mutableStateOf(false) }

    DisposableEffect(authViewModel) {

        val observerEmailState = Observer<String> { _emailState ->
            emailState.value = _emailState
        }
        authViewModel.emailState.observeForever(observerEmailState)

        val observerPasswordState = Observer<String> { _passwordState ->
            passwordState.value = _passwordState
        }
        authViewModel.passwordState.observeForever(observerPasswordState)

        val observerSecondPasswordState = Observer<String> { _secondPasswordState ->
            secondPasswordState.value = _secondPasswordState
        }
        authViewModel.secondPasswordState.observeForever(observerSecondPasswordState)
        onDispose {
            authViewModel.emailState.removeObserver(observerEmailState)
            authViewModel.passwordState.removeObserver(observerPasswordState)
            authViewModel.secondPasswordState.observeForever(observerSecondPasswordState)
        }
    }
    var checked by remember {
        mutableStateOf(false)
    }
    val updatePasswordMatchState: () -> Unit = {
        passwordsMatchState = authViewModel.checkPasswordMatch(passwordState.value, secondPasswordState.value)
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

        TextFieldEmail(
            stringResource(R.string.sign_in_email),
            emailState,
            onValueChange = { newValue -> emailState.value = newValue })
        Spacer(modifier = Modifier.height(15.dp))
        // Передать сюда состояния с вьюмодели для пароля
        TextFieldPass(
            stringResource(R.string.sign_in_password),
            passwordState = passwordState,
            secondPasswordState = secondPasswordState,
            onValueChange = { newValue -> passwordState.value = newValue }
        )
        Spacer(modifier = Modifier.height(15.dp))
        TextFieldPass(
            stringResource(R.string.sign_in_retrypassword),
            secondPassword = true,
            passwordState,
            secondPasswordState = secondPasswordState,
            onValueChange = {
                newValue -> secondPasswordState.value = newValue
                updatePasswordMatchState()
            },
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
            onClick = { // ПОФИКСИТЬ БЭКСТЭК
                if (passwordsMatchState && passwordState.value != "" && emailState.value != "" && checked) {
                    CoroutineScope(Dispatchers.IO).launch {
                        authViewModel.registration(emailState.value, passwordState.value, authApi)
                        sleep(1000)
                    }
                    CoroutineScope(Dispatchers.IO).launch {
                        authViewModel.authorization(emailState.value, passwordState.value, authApi)
                    }
                    navController.navigate(GraphRoute.MAIN) {
                        navController.popBackStack(AuthScreen.Login.route, true)
                    }
                } else if (passwordState.value == "" || emailState.value == "") {
                    Toast.makeText(
                        context,
                        "Почта или пароль не введены",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (checked == false) {
                    Toast.makeText(
                        context,
                        "Условия политики конфиденциальности не приняты",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        context,
                        "Пароли не совпадают",
                        Toast.LENGTH_SHORT
                    ).show()
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
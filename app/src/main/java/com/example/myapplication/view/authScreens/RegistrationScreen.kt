package com.example.myapplication.view.authScreens

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
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.view.camerasBookNProfile.itemsInCameras.BackButton
import com.example.myapplication.view.camerasBookNProfile.itemsInCameras.TextFieldCustom
import com.example.myapplication.view.navigation.GraphRoute


@Composable
fun RegistrationScreen(navController: NavController) {
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

        Text(
            stringResource(R.string.sign_up_title),
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.padding(bottom = 110.dp)
        )

        TextFieldCustom(stringResource(R.string.sign_in_email))
        Spacer(modifier = Modifier.height(15.dp))
        TextFieldCustom(stringResource(R.string.sign_in_password))
        Spacer(modifier = Modifier.height(15.dp))
        TextFieldCustom(stringResource(R.string.sign_in_retrypassword))

        Row(
            modifier = Modifier.padding(start = 30.dp, top = 30.dp)
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = { _checked ->
                    checked = _checked
                },
                modifier = Modifier.size(50.dp).padding(end = 10.dp)
            )
            Text(stringResource(R.string.sign_up_confidence),
                style = MaterialTheme.typography.titleSmall)
        }

        Button(
            onClick = { // забывать про предыдущий экран
                navController.navigate(GraphRoute.MAIN) },
            shape = RoundedCornerShape(30),
            modifier = Modifier.width(140.dp).height(80.dp).padding(top = 30.dp),

        ) {
            Text(stringResource(R.string.sign_in_tb_to_signup),
                style = MaterialTheme.typography.labelMedium)
        }

    }


}
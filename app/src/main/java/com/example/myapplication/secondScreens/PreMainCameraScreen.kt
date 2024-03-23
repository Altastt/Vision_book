package com.example.myapplication.secondScreens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.itemsOfScreen.TextFieldCustom
import com.example.myapplication.models.NavigationItems
import com.example.myapplication.ui.theme.sourceSans

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
            style = TextStyle(
                fontFamily = sourceSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 30.sp
            )
        )
        Text(
            text = stringResource(R.string.premaincamera_newbook_text),
            style = TextStyle(
                fontFamily = sourceSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            ),
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
                    style = TextStyle(
                        fontFamily = sourceSans,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    )
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
                    style = TextStyle(
                        fontFamily = sourceSans,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    )
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
                        style = TextStyle(
                            fontFamily = sourceSans,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp
                        )
                    )
                }

            }
        }
    }
}
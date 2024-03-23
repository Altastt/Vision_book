package com.example.myapplication.secondScreens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.sourceSans

@Composable
fun CanceledPermissonScreen() {
    Column (
        modifier = Modifier.fillMaxHeight().padding(start = 12.dp, end = 12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(R.drawable.sad_permission_canceled),
            contentDescription = "Permission canceled",
            modifier = Modifier.padding(bottom = 20.dp)

        )
        Text(
            text = stringResource(R.string.permission_canceled),
            modifier = Modifier.padding(bottom = 10.dp),
            style = TextStyle(
                fontFamily = sourceSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 25.sp
            )
        )
        Text(
            text = stringResource(R.string.permission_canceled_request),
            style = TextStyle(
                fontFamily = sourceSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
        )
    }
}
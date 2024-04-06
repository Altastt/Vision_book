package com.example.visionbook.view.camerasBookNProfile.secondCameraScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.visionbook.R
import com.example.visionbook.models.AutoresizedText

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
        AutoresizedText(
            text = stringResource(R.string.permission_canceled),
            modifier = Modifier.padding(bottom = 10.dp),
            style = MaterialTheme.typography.titleLarge
        )
        AutoresizedText(
            text = stringResource(R.string.permission_canceled_request),
            style = MaterialTheme.typography.titleMedium
        )
    }
}
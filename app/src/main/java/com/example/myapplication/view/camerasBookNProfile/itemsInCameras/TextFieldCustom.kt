package com.example.myapplication.view.camerasBookNProfile.itemsInCameras

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.sourceSans

@Composable
fun TextFieldCustom (placeholder: String) {
    val value = remember { mutableStateOf("") }
    TextField(
        value = value.value,
        singleLine = true,
        onValueChange = { newText -> value.value = newText },
        shape = RoundedCornerShape(percent = 30),
        textStyle = MaterialTheme.typography.titleMedium,
        trailingIcon = {
            IconButton(onClick = { value.value = "" }) {
                Icon(
                    painterResource(R.drawable.close),
                    "close",
                )
            }
        },
        placeholder = {
            Text(
                placeholder,
                style = MaterialTheme.typography.titleMedium
            )
        },
        // убираю нижнее подчеркивание
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
        )
    )
}
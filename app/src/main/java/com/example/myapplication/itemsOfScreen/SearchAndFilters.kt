package com.example.myapplication.itemsOfScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.DarkGrey
import com.example.myapplication.ui.theme.Orange
import com.example.myapplication.ui.theme.sourceSans

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAndFilters() {
    Row(modifier = Modifier.fillMaxWidth()) {
        val value = remember { mutableStateOf("") }
        // ПРОВЕРИТЬ: keyboardActions (сделать enter вводом)
        TextField(
            value = value.value,
            modifier = Modifier.fillMaxWidth(0.8f),
            singleLine = true,
            onValueChange = { newText -> value.value = newText },
            shape = RoundedCornerShape(percent = 30),
            textStyle = TextStyle(
                color = DarkGrey,
                fontFamily = sourceSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            ),
            trailingIcon = {
                IconButton(onClick = { value.value = "" }) {
                    Icon(
                        painterResource(R.drawable.close),
                        "search",
                        tint = DarkGrey
                    )
                }
            },
            placeholder = {
                Text(
                    "Поиск...",
                    style = TextStyle(
                        color = DarkGrey,
                        fontFamily = sourceSans,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    )
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Orange,
                disabledTextColor = DarkGrey,
                disabledLeadingIconColor = DarkGrey,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )


        // изменить внешний вид нажатия
        Button(
            onClick = { },
            modifier = Modifier.padding(start = 15.dp).size(58.dp).fillMaxWidth(),
            shape = RoundedCornerShape(percent = 30),
            contentPadding = PaddingValues(start = 10.dp, end = 10.dp, top = 15.dp, bottom = 15.dp),
            colors = ButtonDefaults.buttonColors(Orange),
            elevation = ButtonDefaults.elevatedButtonElevation(0.dp)

        ) {
            Image(
                painterResource(R.drawable.filters), "filters"
            )
        }

    }
}
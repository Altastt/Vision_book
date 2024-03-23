package com.example.myapplication.itemsOfScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.sourceSans

@Composable
fun SearchAndFilters() {
    Row(modifier = Modifier.fillMaxWidth()) {
        TextFieldCustom(stringResource(R.string.search))
        Button(
            onClick = { },
            modifier = Modifier.padding(start = 15.dp).size(58.dp).fillMaxWidth(),
            shape = RoundedCornerShape(percent = 30),
            contentPadding = PaddingValues(start = 10.dp, end = 10.dp, top = 15.dp, bottom = 15.dp),
            elevation = ButtonDefaults.elevatedButtonElevation(0.dp)

        ) {
            Icon(
                painterResource(R.drawable.filters), "filters",
            )
        }

    }
}
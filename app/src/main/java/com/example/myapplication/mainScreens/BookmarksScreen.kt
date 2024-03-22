package com.example.myapplication.mainScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R

@Composable
fun BookmarksScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp, start = 12.dp, end = 12.dp),
        // parameters set to place the items in center
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(R.drawable.bookmark),
            contentDescription = "Bookmarks",
            tint = Color(0xFF0F9D58)
        )
        Text(text = "Bookmarks", color = Color.Black)
    }
}

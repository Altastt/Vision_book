package com.example.myapplication.view.mainScreens.itemsInLists

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.data.TestDataItem
import com.example.myapplication.models.NavigationItems
import com.example.myapplication.models.NoRippleInteractionSource

@Composable
fun HomeScreenItems(image: TestDataItem, navController: NavController) {
    Surface(shape = RoundedCornerShape(percent = 10)) {
        Box(contentAlignment = Alignment.BottomEnd,
            modifier = Modifier.clickable { navController.navigate(NavigationItems.Post.route) }) {
            AsyncImage(
                model = image.url, contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
                    .wrapContentHeight()
            )
            var checked by remember {
                mutableStateOf(false) // initially checked
            }
            IconToggleButton(
                modifier = Modifier.padding(bottom = 10.dp, end = 10.dp)
                    .then(
                        Modifier.size(21.dp)
                    ),
                checked = checked,
                onCheckedChange = { _checked ->
                    checked = _checked
                },
                interactionSource = NoRippleInteractionSource()
            ) {
                Icon(
                    painter = painterResource(R.drawable.like),
                    "Like",
                )
            }
        }
    }
}
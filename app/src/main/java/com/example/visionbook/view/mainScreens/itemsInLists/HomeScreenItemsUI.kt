package com.example.visionbook.view.mainScreens.itemsInLists

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
import com.example.visionbook.R
import com.example.visionbook.data.DataBooksScreen
import com.example.visionbook.models.NavigationItems
import com.example.visionbook.models.NoRippleInteractionSource
import com.example.visionbook.viewmodels.AuthVM

@Composable
fun HomeScreenItems(navController: NavController, authViewModel: AuthVM) {


    val randomIndex = (0 until DataBooksScreen.coverList.size).random()
    Surface(
        shape = RoundedCornerShape(10)
    ) {
        Box(contentAlignment = Alignment.BottomEnd,
            modifier = Modifier.clickable {
                navController.navigate(NavigationItems.Post.route)
            }
        ) {
            // bitmap?.let {
            AsyncImage(
                DataBooksScreen.imageList[randomIndex],
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth().wrapContentHeight()
            )
            //   }
            var checked by remember {
                mutableStateOf(false)
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
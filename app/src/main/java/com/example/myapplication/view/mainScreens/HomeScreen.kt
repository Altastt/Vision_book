package com.example.myapplication.view.mainScreens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.view.mainScreens.itemsInLists.HomeScreenItems


@Composable
fun HomeScreen(navController: NavController) {

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 12.dp,
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        content = {
            items(2) {
                Spacer(modifier = Modifier.height(60.dp))
            }
            items(50) {
                HomeScreenItems(navController)
            }
        },
        modifier = Modifier.fillMaxSize().padding(start = 12.dp, end = 12.dp)
    )
}




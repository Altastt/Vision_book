package com.example.myapplication.mainScreens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.data.DataBooksScreen
import com.example.myapplication.data.TestDataItem
import com.example.myapplication.itemsOfScreen.HomeScreenItems
import kotlin.random.Random


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
            items(TestData.testItemsList) { image ->
                HomeScreenItems(image, navController)
            }
        },
        modifier = Modifier.fillMaxSize().padding(start = 12.dp, end = 12.dp)
    )
}


object TestData {
    val testItemsList = List(50) {
        val randomIndex = Random.nextInt(DataBooksScreen.imagesList.size)
        TestDataItem(
            id = Random.nextInt(100, 100000).toString(),
            // Картинки пока что и в книгах, и в главном экране одинаковы
            url = DataBooksScreen.imagesList[randomIndex],
            title = "Title $it"
        )
    }
}

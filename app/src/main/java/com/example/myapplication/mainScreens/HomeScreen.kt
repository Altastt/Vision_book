package com.example.myapplication.mainScreens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.*
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
// прогружать некоторое количество, после чего включать загрузку следующей пачки фото для предотвращения лагов (определить количество элементов пачки)
            items(TestData.testItemsList){image ->
                HomeScreenItems(image, navController)
            }
        },
        modifier = Modifier.fillMaxSize().padding(top = 40.dp, start = 12.dp, end = 12.dp)
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

package com.example.myapplication.mainScreens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.myapplication.data.BooksItem
import com.example.myapplication.data.DataBooksScreen
import com.example.myapplication.itemsOfScreen.BooksScreenItems
import kotlin.random.Random


@Composable
fun BooksScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(top = 46.dp)
    ) {
        items(Books.booksList) { book ->
            BooksScreenItems(book)
            HorizontalDivider(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp).
                clip(RoundedCornerShape(50)
            ),
                thickness = 3.dp,
                color = MaterialTheme.colorScheme.primaryContainer)
        }
    }
}
object Books {
    val booksList = List(20) {
        val randomIndex = Random.nextInt(DataBooksScreen.imagesList.size)
        BooksItem(
            id = Random.nextInt(100, 100000).toString(),
            url = DataBooksScreen.imagesList[randomIndex],
            title = "Title $it",
            author = DataBooksScreen.authorsList[randomIndex],
            name = DataBooksScreen.nameList[randomIndex],
            genre = DataBooksScreen.genreList[randomIndex]
        )
    }
}

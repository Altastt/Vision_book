package com.example.myapplication.view.mainScreens.itemsInLists

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.data.BooksItem


@Composable
fun BooksScreenItems(book: BooksItem) {
    Card(
        modifier = Modifier
            .padding(top = 5.dp, bottom = 5.dp, start = 12.dp, end = 12.dp)
            .clickable {  },
        shape = RoundedCornerShape(40.dp),
        )
    {
        Row(modifier = Modifier.fillMaxSize()) {
            Surface(shape = RoundedCornerShape(30),
                modifier = Modifier.size(144.dp),
                content = {
                    AsyncImage(
                        model = book.url, contentDescription = "",
                        contentScale = ContentScale.Crop,
                    )
                })
            Column(modifier = Modifier.padding(start = 23.dp, top = 10.dp)) {
                Text(
                    "«${book.name}»",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    book.author,
                    modifier = Modifier.padding(top = 3.dp),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    "${stringResource(R.string.genre)}: ${book.genre}",
                    modifier = Modifier.padding(top = 12.dp),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
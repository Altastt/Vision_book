package com.example.visionbook.view.mainScreens.itemsInLists

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.visionbook.R
import com.example.visionbook.models.AutoresizedText
import com.example.visionbook.models.dataclasses.BooksModel


@Composable
fun BooksScreenItems(book: BooksModel) {
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
                    // добавить в саму книгу картинку
                    AsyncImage(
                        model = "", contentDescription = "",
                        contentScale = ContentScale.Crop,
                    )
                })
            Column(modifier = Modifier.padding(start = 23.dp, top = 10.dp)) {
                AutoresizedText(
                    "«${book.title}»",
                    style = MaterialTheme.typography.titleMedium
                )
                AutoresizedText(
                    book.author,
                    modifier = Modifier.padding(top = 3.dp),
                    style = MaterialTheme.typography.titleMedium
                )
                AutoresizedText(
                    "${stringResource(R.string.genre)}: ${book.genre}",
                    modifier = Modifier.padding(top = 12.dp),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
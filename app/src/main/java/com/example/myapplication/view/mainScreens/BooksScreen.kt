package com.example.myapplication.view.mainScreens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.view.mainScreens.itemsInLists.BooksScreenItems
import com.example.myapplication.viewmodels.BooksScreenVM


@Composable
fun BooksScreen(viewModel: BooksScreenVM = viewModel()) {

    val booksList = viewModel.booksList.value

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        item {
            Spacer(Modifier.padding(top = 60.dp))
        }
        items(booksList) { book ->
            BooksScreenItems(book = book)
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp).clip(
                        RoundedCornerShape(50)
                    ),
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}


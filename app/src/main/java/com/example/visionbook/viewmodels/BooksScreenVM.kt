package com.example.visionbook.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.visionbook.data.BooksItem
import com.example.visionbook.data.DataBooksScreen

class BooksScreenVM: ViewModel() {
    // Список книг
    private val _booksList: MutableState<List<BooksItem>> = mutableStateOf(generateBooksList())
    val booksList: MutableState<List<BooksItem>> = _booksList
    // Генерация списка книг
    private fun generateBooksList(): List<BooksItem> {
        val books = mutableListOf<BooksItem>()
        repeat(DataBooksScreen.genreList.size) { index ->
            val id = index.toString()
            val name = "Book ${index + 1}"
            val url = DataBooksScreen.coverList[index]
            val author = DataBooksScreen.authorsList[index]
            val title = DataBooksScreen.titleList[index]
            val genre = DataBooksScreen.genreList[index]
            val book = BooksItem(id, name, url, author, title, genre)
            books.add(book)
        }
        return books
    }
}
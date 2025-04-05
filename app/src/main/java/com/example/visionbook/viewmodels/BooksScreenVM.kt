package com.example.visionbook.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.visionbook.data.BooksItem
import com.example.visionbook.data.DataBooksScreen
import com.example.visionbook.models.dataclasses.BookModelToShare
import com.example.visionbook.models.dataclasses.BookToHistory
import com.example.visionbook.models.dataclasses.BookToShareModel
import com.example.visionbook.models.dataclasses.BookToShareModelResponse
import com.example.visionbook.models.dataclasses.BooksModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BooksScreenVM: ViewModel() {

    private val _titleState = MutableLiveData<String>()
    val titleState: LiveData<String> = _titleState
    private val _authorState = MutableLiveData<String>()
    val authorState: LiveData<String> = _authorState
    private val _genreState = MutableLiveData<String>()
    val genreState: LiveData<String> = _genreState
   /* private val _query = MutableLiveData("")
    val query: LiveData<String> = _query*/

    private val _booksList = MutableLiveData<List<BooksModel>>()
    val booksList: LiveData<List<BooksModel>> = _booksList

    /*fun updateQuery(newQuery: String) {
        _query.value = newQuery
    }*/


    suspend fun getListOfBooks(
        token: String,
        amount: Int,
    ) {
        viewModelScope.launch(Dispatchers.IO) {

        }
    }

    // ДЛЯ ПОИСКА
 /*   suspend fun getBookByName(bookApi: BooksApi, token: String, name: String) {
        viewModelScope.launch {
            try {
               // val bookList = bookApi.getBookByName(token, name)
              //  _productList.postValue(productList.products)
            } catch (e: Exception) {
                // Обработка ошибок
                e.printStackTrace()
            }
        }
    }*/
    suspend fun addBookToSharedList(
        token: String,
        author: String,
        genre: String,
        title: String,
        onComplete: (BookToShareModelResponse) -> Unit, // Лямбда-выражение для передачи idBook
        onError: (Exception) -> Unit // Лямбда-выражение для обработки ошибок
    ) {
        viewModelScope.launch(Dispatchers.IO) {

        }
    }



}

class BooksScreenVM1: ViewModel() {
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
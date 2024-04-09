package com.example.visionbook.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.visionbook.models.api.BooksApi
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
        bookApi: BooksApi,
        amount: Int,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
               val listOfBooks = withContext(Dispatchers.Main) {
                    bookApi.getBooks(
                        token,
                        amount
                    ).book
                }
                _booksList.postValue(listOfBooks)
            } catch (e: Exception) {
                e.printStackTrace()
            }
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
        bookApi: BooksApi,
        author: String,
        genre: String,
        title: String,
        onComplete: (BookToShareModelResponse) -> Unit, // Лямбда-выражение для передачи idBook
        onError: (Exception) -> Unit // Лямбда-выражение для обработки ошибок
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val idBook = withContext(Dispatchers.Main) {
                    bookApi.addBookToSharedList(
                        token,
                        BookToShareModel(
                            BookModelToShare(
                                author = author,
                                title = title,
                                genre = genre
                            ),
                            image = "null"
                        )
                    )
                }
                onComplete(idBook)
            } catch (e: Exception) {
                e.printStackTrace()
                onError(e)
            }
        }
    }


    suspend fun addBookToHistory(token: String, bookApi: BooksApi, idBook: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                withContext(Dispatchers.Main) {
                    bookApi.addBookToHistory(
                        token,
                        BookToHistory(
                            idBook
                        )
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
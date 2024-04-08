package com.example.visionbook.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.visionbook.models.api.BooksApi
import com.example.visionbook.models.dataclasses.BookIdModel
import com.example.visionbook.models.dataclasses.BookModel
import com.example.visionbook.models.dataclasses.BookToShareModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BooksScreenVM(private val coroutineScope: CoroutineScope) : ViewModel() {

    private val _titleState = MutableLiveData<String>()
    val titleState: LiveData<String> = _titleState
    private val _authorState = MutableLiveData<String>()
    val authorState: LiveData<String> = _authorState
    private val _genreState = MutableLiveData<String>()
    val genreState: LiveData<String> = _genreState
    private val _query = MutableLiveData("")
    val query: LiveData<String> = _query

    fun updateQuery(newQuery: String) {
        _query.value = newQuery
    }
    suspend fun getListOfBooks(token: String, bookApi: BooksApi, amount: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                withContext(Dispatchers.Main) {
                    bookApi.getBooks(
                        token,
                        amount
                    )
                }
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
        title: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                withContext(Dispatchers.Main) {
                    bookApi.addBookToSharedList(
                        token,
                        BookToShareModel(
                            BookModel(
                                author = author,
                                title = title,
                                genre = genre
                            ),
                            image = "null"
                        )
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    suspend fun addBookToHistory(token: String, bookApi: BooksApi, idBook: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                withContext(Dispatchers.Main) {
                    bookApi.addBookToHistory(
                        token,
                        BookIdModel(
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
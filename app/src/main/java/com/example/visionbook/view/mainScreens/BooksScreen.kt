package com.example.visionbook.view.mainScreens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.visionbook.models.api.BooksApi
import com.example.visionbook.models.dataclasses.BooksModel
import com.example.visionbook.view.mainScreens.itemsInLists.BooksScreenItems
import com.example.visionbook.viewmodels.AuthVM
import com.example.visionbook.viewmodels.BooksScreenVM
import com.example.visionbook.viewmodels.RetrofitVM


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BooksScreen(
    booksViewModel: BooksScreenVM = viewModel(),
    retrofitViewModel: RetrofitVM = viewModel(), authViewModel: AuthVM
) {
    val bookApi = retrofitViewModel.retrofit.create(BooksApi::class.java)
    val tokenState = remember { mutableStateOf("") }

    //  val queryState = remember { mutableStateOf("") }
    // val activeState = remember { mutableStateOf(false) }

    val bookListState = remember { mutableStateOf<List<BooksModel>?>(null) }
    val tabItems = listOf("Книги", "История сканирования")
    val amount = 30
    /* val items = remember {
         mutableListOf(
             "Honor"
         )
     }*/
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState { tabItems.size }
    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress) {
            selectedTabIndex = pagerState.currentPage
        }
    }
    LaunchedEffect(booksViewModel) {
        booksViewModel.getListOfBooks(bookApi = bookApi, token = tokenState.value, amount = amount)
    }
    // Получение текста из ViewModel
    DisposableEffect(booksViewModel) {
        val observerBooksList = Observer<List<BooksModel>> { _booksList ->
            bookListState.value = _booksList
        }
        booksViewModel.booksList.observeForever(observerBooksList)
        val observerToken = Observer<String> { token ->
            tokenState.value = token
        }
        authViewModel.tokenState.observeForever(observerToken)
        /* val observerQuery = Observer<String> { query ->
             queryState.value = query
         }*/
        //  booksViewModel.query.observeForever(observerQuery)
        onDispose {
            booksViewModel.booksList.observeForever(observerBooksList)
            authViewModel.tokenState.observeForever(observerToken)
            //     booksViewModel.query.observeForever(observerQuery)
        }

    }
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.padding(top = 60.dp))
        TabRow(selectedTabIndex = selectedTabIndex) {
            tabItems.forEachIndexed() { index, item ->
                Tab(selected = index + 1 == selectedTabIndex,
                    onClick = {
                        selectedTabIndex = index
                    },
                    text = {
                        Text(item)
                    }
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { index ->
            if (index == 0) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 80.dp)
                ) {
                    bookListState.value?.let { bookList ->
                        items(bookList) { book ->
                            BooksScreenItems(book = book)
                            HorizontalDivider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 20.dp, end = 20.dp)
                                    .clip(
                                        RoundedCornerShape(50)
                                    ),
                                thickness = 2.dp,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }
                    }
                }
            } else {
                LazyColumn {

                }
            }

        }

    }
}
// РЕАЛИЗАЦИЯ ПОИСКА
//bookListState.value?.let { bookList ->
/* SearchBar(
     query = queryState.value,
     onQueryChange = {
         activeState.value = true
         booksViewModel.updateQuery(it)
     },
     modifier = Modifier.fillMaxWidth(),
     shape = RoundedCornerShape(0),
     onSearch = {
         items.add(queryState.value)
         *//*CoroutineScope(Dispatchers.IO).launch {
                    booksViewModel.getBookByName(bookApi, token = tokenState.value, queryState.value)
                }*//*
                activeState.value = false

            },
            active = activeState.value,
            onActiveChange = {
                activeState.value = it
            },
            placeholder = { Text("Search") },
            trailingIcon = {
                if (activeState.value) {
                    Icon(painterResource(R.drawable.close), "Close",
                        modifier = Modifier.size(30.dp).clickable {
                            if (queryState.value.isNotEmpty()) {
                                queryState.value = ""
                            } else {
                                activeState.value = false
                            }
                        })
                }
            }
        ) {
            items.forEach {
                Row(
                    modifier = Modifier.padding(14.dp)
                ) {
                    Icon(
                        painterResource(R.drawable.history), "History",
                        modifier = Modifier.padding(end = 10.dp).size(30.dp)
                    )
                    Text(text = it)
                }
            }
        }*/

//}

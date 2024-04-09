package com.example.visionbook.view.camerasBookNProfile.secondCameraScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.visionbook.R
import com.example.visionbook.models.AutoresizedText
import com.example.visionbook.models.NavigationItems
import com.example.visionbook.models.api.BooksApi
import com.example.visionbook.view.camerasBookNProfile.itemsInCameras.TextFieldCustom
import com.example.visionbook.viewmodels.AuthVM
import com.example.visionbook.viewmodels.BooksScreenVM
import com.example.visionbook.viewmodels.RetrofitVM
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Thread.sleep

@Composable
fun PreMainCameraScreen(
    booksViewModel: BooksScreenVM = viewModel(),
    retrofitViewModel: RetrofitVM = viewModel(),
    navController: NavController,
    authViewModel: AuthVM
) {
    val bookApi = retrofitViewModel.retrofit.create(BooksApi::class.java)
    val tokenState = remember { mutableStateOf("") }
    val titleState = remember { mutableStateOf("") }
    val authorState = remember { mutableStateOf("") }
    val genreState = remember { mutableStateOf("") }

    var idBook = 0

    DisposableEffect(booksViewModel) {
        val observerTitle = Observer<String> { _titleState ->
            titleState.value = _titleState
        }
        booksViewModel.titleState.observeForever(observerTitle)
        val observerAuthor = Observer<String> { _authorState ->
            authorState.value = _authorState
        }
        booksViewModel.authorState.observeForever(observerAuthor)
        val observerGenre = Observer<String> { _genreState ->
            genreState.value = _genreState
        }
        booksViewModel.genreState.observeForever(observerGenre)
        val observerToken = Observer<String> { token ->
            tokenState.value = token
        }
        authViewModel.tokenState.observeForever(observerToken)

        onDispose {
            booksViewModel.titleState.removeObserver(observerTitle)
            booksViewModel.authorState.removeObserver(observerAuthor)
            booksViewModel.genreState.removeObserver(observerGenre)
            authViewModel.tokenState.observeForever(observerToken)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp, start = 22.dp, end = 22.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var showAdditionalFields by remember { mutableStateOf(false) }

        Text(
            modifier = Modifier.padding(bottom = 10.dp),
            text = stringResource(R.string.premaincamera_newbook),
            style = MaterialTheme.typography.headlineLarge
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // dismiss
            // ПЕРЕРИСОВАТЬ ЭКРАН С КНИГАМИ ДЛЯ БОЛЕЕ ЧЕТКОЙ ЛОГИКИ
            // СКОРЕЕ ВСЕГО ДЕЛАТЬ ЭКРАН КНИГИИИИИИ
            Button(
                onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        booksViewModel.addBookToHistory(tokenState.value, bookApi, 4) // IDBOOK ОТКУДАТО БРАТЬ И СОХРАНЯТЬ
                    }
                    navController.navigate(NavigationItems.Books.route)
                }
            ) {
                AutoresizedText(
                    text = stringResource(R.string.premaincamera_newbook_choose),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            // confirm
            Button(
                onClick = {
                    showAdditionalFields = true
                }
            ) {
                AutoresizedText(
                    text = stringResource(R.string.premaincamera_newbook_bt),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
        if (showAdditionalFields) {
            Column(
                modifier = Modifier.padding(top = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextFieldCustom(
                    stringResource(R.string.name),
                    titleState = titleState,
                    authorState = authorState,
                    genreState = genreState,
                    onValueChange = { newValue -> titleState.value = newValue }
                    )
                Spacer(modifier = Modifier.padding(top = 10.dp))
                TextFieldCustom(
                    stringResource(R.string.author),
                    author = true,
                    titleState = titleState,
                    authorState = authorState,
                    genreState = genreState,
                    onValueChange = { newValue -> authorState.value = newValue }
                    )
                Spacer(modifier = Modifier.padding(top = 10.dp))
                TextFieldCustom(
                    stringResource(R.string.genre),
                    genre = true,
                    titleState = titleState,
                    authorState = authorState,
                    genreState = genreState,
                    onValueChange = { newValue -> genreState.value = newValue }
                    )

                Button(
                    modifier = Modifier.padding(top = 20.dp),
                    onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            booksViewModel.addBookToSharedList(
                                tokenState.value,
                                author = authorState.value,
                                genre = genreState.value,
                                title = titleState.value,
                                bookApi = bookApi,
                                onComplete = { _idBook ->
                                    idBook = _idBook.idBook
                                },
                                onError = { e ->
                                    e.printStackTrace()
                                }
                            )
                            sleep(1000)
                        }
                        CoroutineScope(Dispatchers.IO).launch {
                            booksViewModel.addBookToHistory(tokenState.value, bookApi, idBook)
                        }
                        navController.navigate(NavigationItems.Camera.route)
                        showAdditionalFields = false
                    }
                ) {
                    AutoresizedText(
                        text = stringResource(R.string.premaincamera_newbook_continue),
                        style = MaterialTheme.typography.titleMedium
                    )
                }

            }
        }
    }
}
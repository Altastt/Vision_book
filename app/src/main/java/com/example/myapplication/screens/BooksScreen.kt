package com.example.myapplication.screens

import CameraPreview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import kotlin.random.Random


@Composable
fun BooksScreen() {

    var showCamera by remember { mutableStateOf(false) }
    // в фильтрах сделать разделение на подтемы
    LazyColumn(
        content = {
            items(Books.booksList){ book ->
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray),
                        onClick = { showCamera = true })
                    {
                        Row (modifier = Modifier.fillMaxSize()) {
                            AsyncImage(model = book.url, contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.wrapContentHeight()
                                    .weight(0.4f))
                            Column() {
                                Text(book.name)
                                Text(book.author)
                                Text(book.genre)
                            }
                        }
                    }
                if (showCamera) {
                    TODO("по нажатию на кнопку через навигацию перейти на вкладку с камерой")
                }
            }
        }
    )
}

data class BooksItem(
    var id: String,
    var title: String,
    var url: String,
    var author: String,
    var name: String,
    var genre: String
)

object Books{
    val imagesList = listOf(
        "https://i.pinimg.com/564x/59/a5/34/59a5344b6dd9eb7089d78e7eefeee9d8.jpg",
        "https://i.pinimg.com/564x/2e/5f/b0/2e5fb01fc7feeb16519ac5cd629ca28f.jpg",
        "https://i.pinimg.com/564x/f4/c7/26/f4c72614d34766e5fbd0e84424fb7fcd.jpg",
        "https://i.pinimg.com/564x/a8/94/7c/a8947cf36b556a2fd1c7a15c85a38d30.jpg",
        "https://i.pinimg.com/564x/fa/9a/21/fa9a21ad77a57e0e5d8df3fa3616bff8.jpg",
        "https://i.pinimg.com/564x/42/26/c8/4226c886bdd6dbac268de6a6fd9a99a6.jpg",
        "https://i.pinimg.com/564x/47/f5/f0/47f5f0e0c0f96991ba08d42026e3bbe4.jpg",
        "https://i.pinimg.com/564x/1b/4d/12/1b4d120ec0a557649cc4789727270e68.jpg",
        "https://i.pinimg.com/564x/e5/d8/9a/e5d89a0ccb9a42cff7dabd9f030449bb.jpg",
        "https://i.pinimg.com/564x/e6/ca/6b/e6ca6b20ce7dc8c2fa2a9145eb6391e4.jpg"
    )
    val authorsList = listOf(
        "Агата Кристи",
        "Лев Толстой",
        "Александр Пушкин",
        "Карен Коулс",
        "Пол Линч",
        "Рей Бредбери",
        "Жюль Верн",
        "Иван Бунин",
        "Иван Тургенев",
        "Николай Гоголь",
    )
    val nameList = listOf(
        "Антоновские яблоки",
        "Мертвые души",
        "Вий",
        "Пиковая дама",
        "Отцы и дети",
        "10 негритят",
        "Вино из одуванчиков",
        "Убить полюбовно",
        "Безликая королева",
        "Песнь пророка",
    )
    val genreList = listOf(
        "роман",
        "сказка",
        "фантастика",
        "детектив",
        "драма",
        "ужасы",
        "боевики",
        "приключение",
        "мистика",
        "фэнтези",
    )

    val booksList = List(20) {
        val randomIndex = Random.nextInt(imagesList.size)
        BooksItem(
            id = Random.nextInt(100, 100000).toString(),
            url = imagesList[randomIndex],
            title = "Title $it",
            author = authorsList[randomIndex],
            name = nameList[randomIndex],
            genre = genreList[randomIndex]
        )
    }
}

package com.example.myapplication.itemsOfScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myapplication.data.BooksItem
import com.example.myapplication.ui.theme.DarkGrey
import com.example.myapplication.ui.theme.sourceSans

@Composable
fun BooksScreenItems(book: BooksItem) {
    Button(
        modifier = Modifier.fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        elevation = ButtonDefaults.elevatedButtonElevation(0.dp),
        shape = RoundedCornerShape(0.dp),
        onClick = { })
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
                    style = TextStyle(
                        color = DarkGrey,
                        fontFamily = sourceSans,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 19.sp
                    )
                )
                Text(
                    book.author,
                    modifier = Modifier.padding(top = 3.dp),
                    style = TextStyle(
                        color = DarkGrey,
                        fontFamily = sourceSans,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 17.sp
                    )
                )
                Text(
                    "Жанр: ${book.genre}",
                    modifier = Modifier.padding(top = 12.dp),
                    style = TextStyle(
                        color = DarkGrey,
                        fontFamily = sourceSans,
                        fontWeight = FontWeight.SemiBold,
                        fontStyle = FontStyle.Italic,
                        fontSize = 16.sp
                    )
                )
            }
        }
    }
}
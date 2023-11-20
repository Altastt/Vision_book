package com.example.myapplication.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.ui.theme.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlin.random.Random
import kotlin.random.nextInt


@Preview
@Composable
fun TopInfo() {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp, top = 50.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            "Начнем творить вместе с ИИ?",
            modifier = Modifier.fillMaxWidth(0.8f),
            style = TextStyle(
                color = DarkGrey,
                fontFamily = sourceSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 35.sp
            )
        )
        Image(
            painter = painterResource(id = R.drawable.brain),
            contentDescription = "brain_img",
            modifier = Modifier.align(alignment = Alignment.CenterVertically)
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun SearchAndFilters() {
    Row(modifier = Modifier.padding(top = 45.dp, start = 12.dp, end = 12.dp)) {

        val value = remember { mutableStateOf("") }

        // ПРОВЕРИТЬ: keyboardActions (сделать enter вводом)
        TextField(
            value = value.value,
            modifier = Modifier.fillMaxWidth(0.8f),
            shape = RoundedCornerShape(percent = 30),
            singleLine = true,
            onValueChange = { newText -> value.value = newText },
            textStyle = TextStyle(
                color = DarkGrey,
                fontFamily = sourceSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            ),
            leadingIcon = { Icon(painterResource(R.drawable.search), "search", tint = DarkGrey) },
            trailingIcon = {
                IconButton(onClick = { value.value = "" }) {
                    Icon(
                        painterResource(R.drawable.close),
                        "search",
                        tint = DarkGrey
                    )
                }
            },
            placeholder = {
                Text(
                    "Search...", style = TextStyle(
                        color = DarkGrey,
                        fontFamily = sourceSans,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp
                    )
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Orange,
                textColor = DarkGrey,
                disabledLeadingIconColor = DarkGrey,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
        // изменить внешний вид нажатия
        Button(
            onClick = { },
            modifier = Modifier.padding(start = 15.dp).size(58.dp).fillMaxWidth(),
            shape = RoundedCornerShape(percent = 30),
            contentPadding = PaddingValues(start = 10.dp, end = 10.dp, top = 15.dp, bottom = 15.dp),
            colors = ButtonDefaults.buttonColors(Orange),
            elevation = ButtonDefaults.elevatedButtonElevation(0.dp)

        ) {
            Image(
                painterResource(R.drawable.filters), "filters"
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun ElementsMainScreen() {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 12.dp,
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        content = {
// прогружать некоторое количество, после чего включать загрузку следующей пачки фото для предотвращения лагов (определить количество элементов пачки)
            items(TestData.testItemsList){image ->
                Surface(shape = RoundedCornerShape(percent = 10)){
                    Box(contentAlignment = Alignment.BottomCenter) {
                        AsyncImage(model = image.url, contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxWidth()
                                .wrapContentHeight())
                        Row(
                            modifier = Modifier.background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.White.copy(alpha = 0.0f),
                                        Color.Black.copy(alpha = 0.4f)
                                    )
                                )
                            )
                                .fillMaxWidth()
                                .padding(start = 15.dp, bottom = 10.dp, end = 15.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            var checked by remember {
                                mutableStateOf(false) // initially checked
                            }
                            // сделать как и с картинками (датакласс и список )
                            Text(
                                "P4ol", style = TextStyle(
                                    color = Color.White,
                                    fontFamily = sourceSans,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp
                                ),
                                modifier = Modifier.padding(top = 10.dp)
                            )
                            IconToggleButton(
                                modifier = Modifier.padding(top = 10.dp)
                                    .then(
                                        Modifier.size(21.dp)
                                    ),
                                checked = checked,
                                onCheckedChange = { _checked ->
                                    checked = _checked
                                },
                                interactionSource = NoRippleInteractionSource()
                            ) {
                                // Заменить иконку
                                Icon(
                                    painter = painterResource(R.drawable.like),
                                    "Like",
                                    tint = if (checked) Orange else Color.White
                                )
                            }
                        }
                    }




                }




                    /*


                    */

            }
        },
        modifier = Modifier.fillMaxSize().padding(top = 40.dp, start = 12.dp, end = 12.dp)
    )
}

class NoRippleInteractionSource : MutableInteractionSource {

    override val interactions: Flow<Interaction> = emptyFlow()

    override suspend fun emit(interaction: Interaction) {}

    override fun tryEmit(interaction: Interaction) = true
}

data class TestDataItem(
    var id: String,
    var title: String,
    var url: String
)
object TestData{
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

    val testItemsList = List(100) {
        val randomIndex = Random.nextInt(imagesList.size)
        TestDataItem(
            id = Random.nextInt(100, 100000).toString(),
            url = imagesList[randomIndex],
            title = "Title $it"
        )
    }
}

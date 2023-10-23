package com.example.myapplication.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow


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
            // добавить крестик как кнопку
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
            item {
                // При добавлении бэка вставить AsyncImage
                // прилепить расположение ряда вниз
                Surface(shape = RoundedCornerShape(percent = 10)){
                        Image(
                            painterResource(R.drawable.bee),
                            contentScale = ContentScale.Crop,
                            contentDescription = "null",
                            modifier = Modifier.fillMaxWidth()
                                .wrapContentHeight().clickable { println("Clicked") }
                        )
                        Row(
                            verticalAlignment = Alignment.Bottom,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxHeight().padding(start = 20.dp, bottom = 20.dp, end = 20.dp),
                        ) {
                            var checked by remember {
                                mutableStateOf(false) // initially checked
                            }
                            Text(
                                "P4ol", style = TextStyle(
                                    color = LightGreyText,
                                    fontFamily = sourceSans,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp
                                )
                            )
                            IconToggleButton(
                                modifier = Modifier.padding(0.dp)
                                    .then(
                                        Modifier.size(20.dp)
                                    ),
                                checked = checked,
                                onCheckedChange = { _checked ->
                                    checked = _checked
                                },
                                interactionSource = NoRippleInteractionSource(),
                            ) {
                                // Заменить иконку
                                Icon(
                                    painter = painterResource(R.drawable.like), "Like",
                                    tint = if (checked) Orange else LightGreyText
                                )
                            }
                        }
                }


            }
            item {
                // При добавлении бэка вставить AsyncImage
                Surface(shape = RoundedCornerShape(percent = 10)) {
                    Image(
                        painterResource(R.drawable.cityhero),
                        contentScale = ContentScale.Crop,
                        contentDescription = "null",
                        modifier = Modifier.fillMaxWidth().wrapContentHeight().clickable { println("Clicked") }
                    )
                }
            }
            item {
                // При добавлении бэка вставить AsyncImage
                Surface(shape = RoundedCornerShape(percent = 10)) {
                    Image(
                        painterResource(R.drawable.fish),
                        contentScale = ContentScale.Crop,
                        contentDescription = "null",
                        modifier = Modifier.fillMaxWidth().wrapContentHeight().clickable { println("Clicked") }
                    )
                }
            }
            item {
                // При добавлении бэка вставить AsyncImage
                Surface(shape = RoundedCornerShape(percent = 10)) {
                    Image(
                        painterResource(R.drawable.dub),
                        contentScale = ContentScale.Crop,
                        contentDescription = "null",
                        modifier = Modifier.fillMaxWidth().wrapContentHeight().clickable { println("Clicked") }
                    )
                }
            }
            item {
                // При добавлении бэка вставить AsyncImage
                Surface(shape = RoundedCornerShape(percent = 10)) {
                    Image(
                        painterResource(R.drawable.kolob),
                        contentScale = ContentScale.Crop,
                        contentDescription = "null",
                        modifier = Modifier.fillMaxWidth().wrapContentHeight().clickable { println("Clicked") }
                    )
                }
            }
            item {
                // При добавлении бэка вставить AsyncImage
                Surface(shape = RoundedCornerShape(percent = 10)) {
                    Image(
                        painterResource(R.drawable.starwars),
                        contentScale = ContentScale.Crop,
                        contentDescription = "null",
                        modifier = Modifier.fillMaxWidth().wrapContentHeight().clickable { println("Clicked") }
                    )
                }
            }
            item {
                // При добавлении бэка вставить AsyncImage
                Surface(shape = RoundedCornerShape(percent = 10)) {
                    Image(
                        painterResource(R.drawable.minecraft),
                        contentScale = ContentScale.Crop,
                        contentDescription = "null",
                        modifier = Modifier.fillMaxWidth().wrapContentHeight().clickable { println("Clicked") }
                    )
                }
            }
            item {
                // При добавлении бэка вставить AsyncImage
                Surface(shape = RoundedCornerShape(percent = 10)) {
                    Image(
                        painterResource(R.drawable.octulos),
                        contentScale = ContentScale.Crop,
                        contentDescription = "null",
                        modifier = Modifier.fillMaxWidth().wrapContentHeight().clickable { println("Clicked") }
                    )
                }
            }
            item {
                // При добавлении бэка вставить AsyncImage
                Surface(shape = RoundedCornerShape(percent = 10)) {
                    Image(
                        painterResource(R.drawable.logo),
                        contentScale = ContentScale.Crop,
                        contentDescription = "null",
                        modifier = Modifier.fillMaxWidth().wrapContentHeight().clickable { println("Clicked") }
                    )
                }
            }
            item {
                // При добавлении бэка вставить AsyncImage
                Surface(shape = RoundedCornerShape(percent = 10)) {
                    Image(
                        painterResource(R.drawable.bee),
                        contentScale = ContentScale.Crop,
                        contentDescription = "null",
                        modifier = Modifier.fillMaxWidth().wrapContentHeight().clickable { println("Clicked") }
                    )
                }
            }
            item {
                // При добавлении бэка вставить AsyncImage
                Surface(shape = RoundedCornerShape(percent = 10)) {
                    Image(
                        painterResource(R.drawable.cityhero),
                        contentScale = ContentScale.Crop,
                        contentDescription = "null",
                        modifier = Modifier.fillMaxWidth().wrapContentHeight().clickable { println("Clicked") }
                    )
                }
            }
            item {
                // При добавлении бэка вставить AsyncImage
                Surface(shape = RoundedCornerShape(percent = 10)) {
                    Image(
                        painterResource(R.drawable.starwars),
                        contentScale = ContentScale.Crop,
                        contentDescription = "null",
                        modifier = Modifier.fillMaxWidth().wrapContentHeight().clickable { println("Clicked") }
                    )
                }
            }
            item {
                // При добавлении бэка вставить AsyncImage
                Surface(shape = RoundedCornerShape(percent = 10)) {
                    Image(
                        painterResource(R.drawable.minecraft),
                        contentScale = ContentScale.Crop,
                        contentDescription = "null",
                        modifier = Modifier.fillMaxWidth().wrapContentHeight().clickable { println("Clicked") }
                    )
                }
            }
        },
        modifier = Modifier.fillMaxSize().padding(top = 40.dp, start = 12.dp, end = 12.dp)
    )

}class NoRippleInteractionSource : MutableInteractionSource {

    override val interactions: Flow<Interaction> = emptyFlow()

    override suspend fun emit(interaction: Interaction) {}

    override fun tryEmit(interaction: Interaction) = true
}


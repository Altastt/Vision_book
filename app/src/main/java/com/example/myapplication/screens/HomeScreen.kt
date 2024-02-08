package com.example.myapplication.screens


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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.ui.theme.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlin.random.Random




@Composable
fun HomeScreen() {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 12.dp,
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        content = {
// прогружать некоторое количество, после чего включать загрузку следующей пачки фото для предотвращения лагов (определить количество элементов пачки)
            items(TestData.testItemsList){image ->
                HomeScreenItems(image)
            }
        },
        modifier = Modifier.fillMaxSize().padding(top = 40.dp, start = 12.dp, end = 12.dp)
    )
}

@Composable
private fun HomeScreenItems(image: TestDataItem) {
    Surface(shape = RoundedCornerShape(percent = 10)) {
        Box(contentAlignment = Alignment.BottomEnd) {
            AsyncImage(
                model = image.url, contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
                    .wrapContentHeight()
            )
            var checked by remember {
                mutableStateOf(false) // initially checked
            }
            IconToggleButton(
                modifier = Modifier.padding(bottom = 10.dp, end = 10.dp)
                    .then(
                        Modifier.size(21.dp)
                    ),
                checked = checked,
                onCheckedChange = { _checked ->
                    checked = _checked
                },
                interactionSource = NoRippleInteractionSource()
            ) {
                Icon(
                    painter = painterResource(R.drawable.like),
                    "Like",
                    tint = if (checked) Orange else Color.LightGray
                )
            }
        }
    }
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



object TestData {
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

    val testItemsList = List(50) {
        val randomIndex = Random.nextInt(imagesList.size)
        TestDataItem(
            id = Random.nextInt(100, 100000).toString(),
            url = imagesList[randomIndex],
            title = "Title $it"
        )
    }
}

package com.example.myapplication.secondScreens

import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.graphics.drawable.toDrawable
import coil.compose.AsyncImage
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.example.myapplication.R
import com.example.myapplication.data.DataBooksScreen
import com.example.myapplication.data.DataPostScreen
import com.example.myapplication.models.NoRippleInteractionSource
import com.example.myapplication.ui.theme.sourceSans
import kotlin.random.Random


@Composable
fun Post(){
    Box(modifier = Modifier.fillMaxSize()) {
        val mContext = LocalContext.current

        val videoUrl = "https://static.videezy.com/system/resources/previews/000/054/572/original/R-10.mp4"
        val retriever = MediaMetadataRetriever()
        retriever.setDataSource(videoUrl)

        val bitmap: Bitmap? = retriever.getFrameAtTime(0, MediaMetadataRetriever.OPTION_CLOSEST)
        val exoPlayer = remember(mContext) {
            ExoPlayer.Builder(mContext).build().apply {
                val dataSourceFactory = DefaultDataSourceFactory(mContext, Util.getUserAgent(mContext, mContext.packageName))
                val mediaItem = MediaItem.fromUri(videoUrl)
                val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(mediaItem)
                setMediaSource(mediaSource)
                prepare()
            }
        }

        AndroidView(modifier = Modifier.fillMaxWidth(),
            factory = { context ->
            PlayerView(context).apply {
                player = exoPlayer
                useController = true
                // Скрываем элементы контроллера по умолчанию
                controllerShowTimeoutMs = 0
                hideController() // Скрываем контроллер при старте
                useArtwork = true // Включаем отображение превью-изображения
                defaultArtwork = bitmap?.toDrawable(context.resources)

            }
        })

        Surface(modifier = Modifier.fillMaxWidth().padding(top = 200.dp),
            shape = RoundedCornerShape(percent = 10)
        ) {
            Column(modifier = Modifier.fillMaxSize()
                .padding(start = 12.dp, end = 12.dp)) {
                Row(modifier = Modifier.fillMaxWidth().padding(bottom = 30.dp, top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {
                    AsyncImage(model = PostElement.avatar, "Avatar",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(70.dp).clip(CircleShape))
                    Text(PostElement.nickname,
                        style = TextStyle(
                            fontFamily = sourceSans,
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp
                        )
                    )
                    var checked by remember {
                        mutableStateOf(false) // initially checked
                    }
                    IconToggleButton(
                        modifier = Modifier.padding(bottom = 10.dp, end = 10.dp)
                            .then(
                                Modifier.size(32.dp)
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
                        )
                    }
                }
                Text("${stringResource(R.string.author)}: ${PostElement.authorofBook}",
                    style = TextStyle(
                        fontFamily = sourceSans,
                        fontWeight = FontWeight.SemiBold,
                        fontStyle = FontStyle.Italic,
                        fontSize = 18.sp
                    ),
                    modifier = Modifier.padding(start = 8.dp, bottom = 7.dp))
                Text("${stringResource(R.string.name)}: ${PostElement.nameBook}",
                    style = TextStyle(
                        fontFamily = sourceSans,
                        fontWeight = FontWeight.SemiBold,
                        fontStyle = FontStyle.Italic,
                        fontSize = 18.sp
                    ),
                    modifier = Modifier.padding(start = 8.dp, bottom = 7.dp))
                Text("${stringResource(R.string.genre)}: ${PostElement.genreBook}",
                    style = TextStyle(
                        fontFamily = sourceSans,
                        fontWeight = FontWeight.SemiBold,
                        fontStyle = FontStyle.Italic,
                        fontSize = 18.sp
                    ),
                    modifier =  Modifier.padding(start = 8.dp, bottom = 40.dp))
                Text("${stringResource(R.string.post_text)}:",
                    style = TextStyle(
                        fontFamily = sourceSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 27.sp
                    ),
                    modifier = Modifier.padding(start = 8.dp, bottom = 26.dp))
                Text("     ${PostElement.text}",
                    style = TextStyle(
                        fontFamily = sourceSans,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    ),
                    modifier = Modifier.padding(start = 8.dp))
            }
        }
    }
}

object PostElement{
    val randomIndex = Random.nextInt(DataBooksScreen.imagesList.size)
    val nickname = DataPostScreen.nicknameList[randomIndex]
    val text = DataPostScreen.textList[randomIndex]
    val avatar = DataPostScreen.avatarList[randomIndex]
    val authorofBook = DataBooksScreen.authorsList[randomIndex]
    val nameBook = DataBooksScreen.nameList[randomIndex]
    val genreBook = DataBooksScreen.genreList[randomIndex]
}


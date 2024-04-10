package com.example.visionbook.view.addScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.graphics.drawable.toDrawable
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.visionbook.R
import com.example.visionbook.models.AutoresizedText
import com.example.visionbook.models.NoRippleInteractionSource
import com.example.visionbook.models.PostScreenVMFactory
import com.example.visionbook.viewmodels.AuthVM
import com.example.visionbook.viewmodels.BooksScreenVM
import com.example.visionbook.viewmodels.ExoPlayerVM
import com.example.visionbook.viewmodels.PostScreenVM
import com.example.visionbook.viewmodels.ProfileScreenVM
import com.google.android.exoplayer2.ui.PlayerView


@Composable
fun Post(
    playerViewModel: ExoPlayerVM = viewModel(),
    authViewModel: AuthVM
){
    val userViewModel = viewModel<ProfileScreenVM>()
    val bookViewModel = viewModel<BooksScreenVM>()
    val postViewModel: PostScreenVM = viewModel<PostScreenVM>(factory = PostScreenVMFactory(userViewModel, bookViewModel))

    val context = LocalContext.current
    val postItem = postViewModel.getPostItem()


    Box(modifier = Modifier.fillMaxSize()) {
        val (exoPlayer, bitmap) = remember { playerViewModel.initializePlayer(postItem.videoUrl, context) }

        DisposableEffect(context) {
            onDispose {
                playerViewModel.releasePlayer()
                bitmap?.recycle()
            }
        }

        AndroidView(
            modifier = Modifier.fillMaxWidth(),
            factory = { context ->
                PlayerView(context).apply {
                    player = exoPlayer
                    useController = true
                    controllerShowTimeoutMs = 0
                    hideController()
                    useArtwork = true
                    defaultArtwork = bitmap?.toDrawable(context.resources)
                }
            }
        )


        Surface(modifier = Modifier.fillMaxWidth().padding(top = 200.dp),
            shape = RoundedCornerShape(percent = 10)
        ) {
            Column(modifier = Modifier.fillMaxSize()
                .padding(start = 12.dp, end = 12.dp)) {
                Row(modifier = Modifier.fillMaxWidth().padding(bottom = 30.dp, top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {
                    AsyncImage(model = postItem.avatarUrl, "Avatar",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(70.dp).clip(CircleShape))
                    AutoresizedText(
                        postItem.username,
                        style = MaterialTheme.typography.labelMedium
                    )
                    var checked by remember {
                        mutableStateOf(false)
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
                AutoresizedText("${stringResource(R.string.author)}: ${postItem.bookAuthor}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 8.dp, bottom = 7.dp))
                AutoresizedText("${stringResource(R.string.name)}: ${postItem.bookTitle}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 8.dp, bottom = 7.dp))
                AutoresizedText("${stringResource(R.string.genre)}: ${postItem.bookGenre}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier =  Modifier.padding(start = 8.dp, bottom = 40.dp))
                AutoresizedText("${stringResource(R.string.post_text)}:",
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(start = 8.dp, bottom = 26.dp))
                Text("     ${postItem.bookText}",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 8.dp))
            }
        }
    }
}


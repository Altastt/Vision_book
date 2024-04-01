package com.example.myapplication.viewmodels

import android.content.Context
import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import androidx.lifecycle.ViewModel
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExoPlayerVM : ViewModel() {
    private var exoPlayer: ExoPlayer? = null
    suspend fun getVideoPreview(videoUrl: String): Bitmap? {
        return withContext(Dispatchers.IO) {
            val retriever = MediaMetadataRetriever()
            retriever.setDataSource(videoUrl)
            return@withContext retriever.getFrameAtTime(0, MediaMetadataRetriever.OPTION_CLOSEST)
        }
    }

    fun initializePlayer(videoUrl: String, context: Context): Pair<ExoPlayer, Bitmap?> {
        if (exoPlayer == null) {
            exoPlayer = SimpleExoPlayer.Builder(context).build()
        }

        val dataSourceFactory = DefaultDataSourceFactory(context, Util.getUserAgent(context, context.packageName))
        val mediaItem = MediaItem.fromUri(videoUrl)
        val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(mediaItem)

        exoPlayer?.setMediaSource(mediaSource)
        exoPlayer?.prepare()

        val retriever = MediaMetadataRetriever()
        retriever.setDataSource(videoUrl)
        val bitmap = retriever.getFrameAtTime(0, MediaMetadataRetriever.OPTION_CLOSEST)

        return Pair(exoPlayer!!, bitmap)
    }

    fun releasePlayer() {
        exoPlayer?.release()
        exoPlayer = null
    }

    fun startPlayer() {
        exoPlayer?.playWhenReady = true
    }

    fun pausePlayer() {
        exoPlayer?.playWhenReady = false
    }
}
package com.example.myapplication.models

import android.content.Context
import com.example.myapplication.R
import java.io.File

class FileHelper(private val context: Context) {
    fun getDirectory(): File {
        val mediaDir = context.externalMediaDirs.firstOrNull()?.let {
            File(it, context.resources.getString(R.string.app_name)).apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists())
            mediaDir else context.filesDir
    }
}


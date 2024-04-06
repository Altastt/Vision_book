package com.example.visionbook.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import com.example.visionbook.viewmodels.BooksScreenVM
import com.example.visionbook.viewmodels.PostScreenVM
import com.example.visionbook.viewmodels.ProfileScreenVM


class PostScreenVMFactory(
    private val userViewModel: ProfileScreenVM,
    private val bookViewModel: BooksScreenVM
) : Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostScreenVM::class.java)) {
            return PostScreenVM(userViewModel, bookViewModel) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

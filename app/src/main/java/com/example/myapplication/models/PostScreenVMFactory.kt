package com.example.myapplication.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import com.example.myapplication.viewmodels.BooksScreenVM
import com.example.myapplication.viewmodels.PostScreenVM
import com.example.myapplication.viewmodels.ProfileScreenVM


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

package com.example.visionbook.viewmodels

import androidx.lifecycle.ViewModel
import com.example.visionbook.data.DataPost
import com.example.visionbook.data.PostItem

class PostScreenVM(
    private val userViewModel: ProfileScreenVM,
    private val bookViewModel: BooksScreenVM,
) : ViewModel() {
    fun getPostItem(): PostItem {
        // Получаем данные о пользователе из UserViewModel
        val username = userViewModel.profileList.value.random().nickname
        val avatarUrl = userViewModel.profileList.value.random().nickname

        // Получаем данные о книге из BookViewModel
        val bookTitle = bookViewModel.titleState.value!!
        val bookGenre = bookViewModel.genreState.value!!
        val bookAuthor = bookViewModel.authorState.value!!

        // Выбираем случайный текст из списка
        val bookText = DataPost.textList.random()
        val videoUrl = DataPost.videoUrlList.random()

        return PostItem(
            username = username,
            avatarUrl = avatarUrl,
            bookTitle = bookTitle,
            bookGenre = bookGenre,
            bookAuthor = bookAuthor,
            bookText = bookText,
            videoUrl = videoUrl
        )
    }

    fun getPostItemsList(size: Int): List<PostItem> {
        val postItems = mutableListOf<PostItem>()
        repeat(size) {
            val postItem = getPostItem()
            postItems.add(postItem)
        }
        return postItems
    }


}

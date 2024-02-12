package com.example.myapplication.models

import com.example.myapplication.R

sealed class NavigationItems(var route: String, var icon: Int, var title: String) {
    object Home : NavigationItems("home", R.drawable.home, "Home")
    object Books : NavigationItems("books", R.drawable.book, "Books")
    object Camera : NavigationItems("camera", R.drawable.camera, "Camera")
    object Bookmarks : NavigationItems("bookbarks", R.drawable.bookmark, "Bookmarks")
    object Profile : NavigationItems("profile", R.drawable.profile, "Profile")
    object Post : NavigationItems("post", R.drawable.profile, "post")
}

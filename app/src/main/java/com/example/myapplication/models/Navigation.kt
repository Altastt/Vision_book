package com.example.myapplication.models

import CameraPreview
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.mainScreens.*
import com.example.myapplication.secondScreens.Post

@Composable
fun Navigation(navController: NavHostController) {

    NavHost(navController, NavigationItems.Home.route) {
        composable(NavigationItems.Home.route) {
            HomeScreen(navController)
        }
        composable(NavigationItems.Books.route) {
            BooksScreen()
        }
        composable(NavigationItems.Camera.route) {
            CameraPreview()
        }
        composable(NavigationItems.Bookmarks.route) {
            BookmarksScreen()
        }
        composable(NavigationItems.Profile.route) {
            ProfileScreen()
        }
        composable(NavigationItems.Post.route) {
            Post()
        }
    }

}

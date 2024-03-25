package com.example.myapplication.models

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.mainScreens.BookmarksScreen
import com.example.myapplication.mainScreens.BooksScreen
import com.example.myapplication.mainScreens.HomeScreen
import com.example.myapplication.mainScreens.ProfileScreen
import com.example.myapplication.secondScreens.Post
import com.example.myapplication.secondScreens.PreMainCameraScreen

@Composable
fun Navigation(navController: NavHostController, onThemeUpdated: () -> Unit) {

    NavHost(navController, NavigationItems.Home.route) {
        composable(NavigationItems.Home.route) {
            HomeScreen(navController)
        }
        composable(NavigationItems.Books.route) {
            BooksScreen()
        }
        composable(NavigationItems.Camera.route) {
            PreMainCameraScreen(navController)
        }
        composable(NavigationItems.Bookmarks.route) {
            BookmarksScreen()
        }
        composable(NavigationItems.Profile.route) {
            ProfileScreen(navController, onThemeUpdated)
        }
        composable(NavigationItems.Post.route) {
            Post()
        }
        composable(NavigationItems.CameraForProfile.route) {
            Camera(LocalContext.current, navController)
        }
    }

}

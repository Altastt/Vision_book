package com.example.myapplication.models

import CameraScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.mainScreens.*
import com.example.myapplication.secondScreens.CameraProfileScreen
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
            CameraScreen()
        }
        composable(NavigationItems.Bookmarks.route) {
            BookmarksScreen()
        }
        composable(NavigationItems.Profile.route) {
            ProfileScreen(navController)
        }
        composable(NavigationItems.Post.route) {
            Post()
        }
        composable(NavigationItems.CameraForProfile.route) {
            CameraProfileScreen(LocalContext.current, navController)
        }
    }

}

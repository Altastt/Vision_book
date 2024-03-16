package com.example.myapplication.models

import CameraScreen
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
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

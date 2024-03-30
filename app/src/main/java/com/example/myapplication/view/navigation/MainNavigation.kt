package com.example.myapplication.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.models.NavigationItems
import com.example.myapplication.view.addScreens.Post
import com.example.myapplication.view.camerasBookNProfile.secondCameraScreens.PreMainCameraScreen
import com.example.myapplication.view.camerasBookNProfile.secondCameraScreens.PreProfileCameraScreen
import com.example.myapplication.view.mainScreens.*

@Composable
fun MainNavigation(navController: NavHostController, onThemeUpdated: () -> Unit) {

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
            SettingsProfileScreen(navController, onThemeUpdated)
        }
        composable(NavigationItems.Post.route) {
            Post()
        }

        profileNavigation(navController)

        settingsNavigation(navController)

    }

}


package com.example.visionbook.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.visionbook.models.NavigationItems
import com.example.visionbook.view.addScreens.Post
import com.example.visionbook.view.camerasBookNProfile.secondCameraScreens.PickImageFromGallery
import com.example.visionbook.view.camerasBookNProfile.secondCameraScreens.PreMainCameraScreen
import com.example.visionbook.view.mainScreens.*
import com.example.visionbook.viewmodels.AuthVM

@Composable
fun MainNavigation(navController: NavHostController, onThemeUpdated: () -> Unit, authViewModel: AuthVM) {

    NavHost(navController, NavigationItems.Home.route) {
        composable(NavigationItems.Home.route) {
            HomeScreen(navController, authViewModel)
        }
        composable(NavigationItems.Books.route) {
            BooksScreen(authViewModel = authViewModel)
        }
        composable(NavigationItems.CameraInMain.route) {
            PreMainCameraScreen(navController = navController, authViewModel = authViewModel)
        }
        composable(NavigationItems.Bookmarks.route) {
            BookmarksScreen(authViewModel)
        }
        composable(NavigationItems.Profile.route) {
            SettingsProfileScreen(navController, onThemeUpdated, authViewModel = authViewModel)
        }
        composable(NavigationItems.Post.route) {
                Post(authViewModel = authViewModel)
        }

        profileNavigation(navController)

        settingsNavigation(navController)

        composable(NavigationItems.Camera.route){
            Camera(LocalContext.current, navController)
        }

        // ВРЕМЕННО
        composable(NavigationItems.Gallery.route){
            PickImageFromGallery()
        }
    }

}


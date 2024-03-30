package com.example.myapplication.view.navigation

import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.myapplication.models.NavigationItems
import com.example.myapplication.view.mainScreens.Camera
import com.example.myapplication.view.settingsScreen.*

fun NavGraphBuilder.settingsNavigation(navController: NavHostController) {
    navigation(
        route = GraphRoute.SETTINGS,
        startDestination = SettingsScreen.ProfileSettings.route
    ){
        composable(SettingsScreen.ProfileSettings.route) {
            ProfileSettingsScreen()
        }
        composable(SettingsScreen.Notification.route) {
            NotificationScreen()
        }
        composable(SettingsScreen.Security.route) {
            SecurityScreen()
        }
        // переделать под задел на подписки в будущем
        composable(SettingsScreen.Language.route) {
            LanguageScreen()
        }
        composable(SettingsScreen.FAQ.route) {
            FAQScreen()
        }

        composable(NavigationItems.CameraForProfile.route) {
            //  PreProfileCameraScreen(navController)
            Camera(LocalContext.current, navController)
        }
    }
}

sealed class SettingsScreen(val route: String) {
    object Notification : SettingsScreen("NOTIFICATION")
    object Security : SettingsScreen("SECURITY")
    object Language : SettingsScreen("LANGUAGE")
    object FAQ : SettingsScreen("FAQ")
    object ProfileSettings : SettingsScreen("PROFILESETTINGS")
}
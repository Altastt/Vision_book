package com.example.visionbook.view.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.visionbook.models.NavigationItems
import com.example.visionbook.view.camerasBookNProfile.secondCameraScreens.PreProfileCameraScreen
import com.example.visionbook.view.settingsScreen.*
import com.example.visionbook.viewmodels.AuthVM

fun NavGraphBuilder.settingsNavigation(navController: NavHostController, authViewModel: AuthVM) {
    navigation(
        route = GraphRoute.SETTINGS,
        startDestination = SettingsScreen.ProfileSettings.route
    ) {
        composable(SettingsScreen.ProfileSettings.route) {
            ProfileSettingsScreen(navController)
        }
        composable(SettingsScreen.Notification.route) {
            NotificationScreen(navController)
        }
        composable(SettingsScreen.Security.route) {
            SecurityScreen(navController)
        }
        // переделать под задел на подписки в будущем
        composable(SettingsScreen.Language.route) {
            LanguageScreen(navController)
        }
        composable(SettingsScreen.FAQ.route) {
            FAQScreen(navController)
        }

        composable(NavigationItems.CameraInProfile.route) {
            PreProfileCameraScreen(navController)
        }
        authNavigation(navController, authViewModel)
    }
}

sealed class SettingsScreen(val route: String) {
    object Notification : SettingsScreen("NOTIFICATION")
    object Security : SettingsScreen("SECURITY")
    object Language : SettingsScreen("LANGUAGE")
    object FAQ : SettingsScreen("FAQ")
    object ProfileSettings : SettingsScreen("PROFILESETTINGS")
}
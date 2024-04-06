package com.example.visionbook.view.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.visionbook.models.NavigationItems
import com.example.visionbook.view.camerasBookNProfile.secondCameraScreens.PreProfileCameraScreen
import com.example.visionbook.view.settingsScreen.*

fun NavGraphBuilder.settingsNavigation(navController: NavHostController) {
    navigation(
        route = GraphRoute.SETTINGS,
        startDestination = SettingsScreen.ProfileSettings.route
    ) {
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

        composable(NavigationItems.CameraInProfile.route) {
            PreProfileCameraScreen(navController)
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
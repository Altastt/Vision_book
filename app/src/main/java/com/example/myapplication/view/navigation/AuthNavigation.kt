package com.example.myapplication.view.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.myapplication.view.authScreens.ForgotScreen
import com.example.myapplication.view.authScreens.LoginScreen
import com.example.myapplication.view.authScreens.RegistrationScreen

fun NavGraphBuilder.authNavigation(navController: NavHostController) {
    navigation(
        route = GraphRoute.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ) {
        composable(AuthScreen.Login.route) {
            LoginScreen(navController)
        }
        composable(AuthScreen.Registration.route) {
            RegistrationScreen(navController)
        }
        composable(AuthScreen.Forgot.route) {
            ForgotScreen(navController)
        }
    }
}

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen("LOGIN")
    object Registration : AuthScreen("REGISTRATION")
    object Forgot : AuthScreen("FORGOT")
}
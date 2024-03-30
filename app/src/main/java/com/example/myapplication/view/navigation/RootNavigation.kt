package com.example.myapplication.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.view.MainScreen

@Composable
fun RootNavigation(navController: NavHostController, onThemeUpdated: () -> Unit) {
    NavHost(
        navController = navController,
        route = GraphRoute.ROOT,
        startDestination = GraphRoute.AUTHENTICATION
    ) {
        composable(GraphRoute.MAIN){
            MainScreen(onThemeUpdated)
        }
        authNavigation(navController)
    }
}

object GraphRoute {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "authentication_graph"
    const val MAIN = "main_graph"
    const val PROFILE = "profile_graph"
    const val SETTINGS = "settings_graph"
}
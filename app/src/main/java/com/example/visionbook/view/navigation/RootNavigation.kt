package com.example.visionbook.view.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.visionbook.view.MainScreen
import com.example.visionbook.viewmodels.AuthVM

@Composable
fun RootNavigation(navController: NavHostController, onThemeUpdated: () -> Unit) {
    val authViewModel: AuthVM = viewModel()
    NavHost(
        navController = navController,
        route = GraphRoute.ROOT,
        startDestination = GraphRoute.AUTHENTICATION
    ) {
        composable(GraphRoute.MAIN){
            MainScreen(onThemeUpdated, authViewModel)
        }
        authNavigation(navController, authViewModel)
    }
}

object GraphRoute {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "authentication_graph"
    const val MAIN = "main_graph"
    const val PROFILE = "profile_graph"
    const val SETTINGS = "settings_graph"
}
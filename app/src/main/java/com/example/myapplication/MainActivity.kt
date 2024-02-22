package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myapplication.models.Navigation


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val topBarState = rememberSaveable { (mutableStateOf(true)) }
    when (navBackStackEntry?.destination?.route) {
        "home" -> { topBarState.value = true }
        "books" -> { topBarState.value = true }
        "camera" -> { topBarState.value = false }
        "bookmarks" -> { topBarState.value = true }
        "profile" -> { topBarState.value = false }
        "post" -> { topBarState.value = false }
    }
    Scaffold(
        topBar = { TopBar(navController, topBarState) },
        content = { padding ->
            Column(modifier = Modifier.padding(top = 26.dp)){
                Navigation(navController)
            }
        },
        bottomBar = { BottomNavigationBar(navController) },
    )
}

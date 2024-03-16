package com.example.myapplication

import AnimatedBottomNavigationBar
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val topBarState = rememberSaveable { (mutableStateOf(true)) }
    val bottomBarState = rememberSaveable { mutableStateOf(true) }
    when (navBackStackEntry?.destination?.route) {
        "home" -> {
            topBarState.value = true
            bottomBarState.value = true
        }
        "books" -> {
            topBarState.value = true
            bottomBarState.value = true
        }
        "camera" -> {
            topBarState.value = false
            bottomBarState.value = true
        }
        "bookmarks" -> {
            topBarState.value = true
            bottomBarState.value = true
        }
        "profile" -> {
            topBarState.value = false
            bottomBarState.value = true
        }
        "post" -> {
            topBarState.value = false
            bottomBarState.value = true
        }
        "cameraforprofile" -> {
            topBarState.value = false
            bottomBarState.value = false
        }
    }
    Scaffold(
        topBar = { AnimatedTopNavigationBar(navController, topBarState) },
        content = {
                Navigation(navController)
        },
        bottomBar = { AnimatedBottomNavigationBar(navController, bottomBarState) },
    )
}

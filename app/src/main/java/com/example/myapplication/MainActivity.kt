package com.example.myapplication

import AnimatedBottomNavigationBar
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.models.Navigation
import com.example.myapplication.ui.theme.MyApplicationTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var darkTheme by remember { mutableStateOf(false) }
            MyApplicationTheme (darkTheme = darkTheme) {
                MainScreen(onThemeUpdated = { darkTheme = !darkTheme })
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(onThemeUpdated: () -> Unit) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
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
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { AnimatedTopNavigationBar(navController, topBarState, scrollBehavior) },
        content = {
            Navigation(navController, onThemeUpdated)
        },
        bottomBar = { AnimatedBottomNavigationBar(navController, bottomBarState) },
    )
}

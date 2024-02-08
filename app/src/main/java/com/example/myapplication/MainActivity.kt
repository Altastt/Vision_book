package com.example.myapplication

import CameraPreview
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myapplication.models.NavigationItems
import com.example.myapplication.screens.*
import com.example.myapplication.ui.theme.DarkGrey
import com.example.myapplication.ui.theme.LightGreyText
import com.example.myapplication.ui.theme.sourceSans

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(rememberNavController())

        }
    }

}



// States
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint
@Composable
fun TopBar(navController: NavController, topAppBarState: MutableState<Boolean>) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val title: String = when (navBackStackEntry?.destination?.route ?: "home") {
        "home" -> "Home"
        "books" -> "Books"
        "camera" -> "Camera"
        "bookmarks" -> "Bookmarks"
        "profile" -> "Profile"
        else -> "Home"
    }
    AnimatedVisibility(
        visible = topAppBarState.value,
        enter = slideInVertically(initialOffsetY = { -it }),
        exit = slideOutVertically(targetOffsetY = { -it }),
        content = {
            TopAppBar(
                title = {
                    // сделать запоминание состояния и менять текст для разных вкладок
                    Text(
                    "Начнем творить вместе с ИИ?",
                    modifier = Modifier.padding(start = 12.dp, end = 20.dp),
                    style = TextStyle(
                        color = DarkGrey,
                        fontFamily = sourceSans,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp)
                )},
                actions = {
                    var showSearchAndFilters by remember { mutableStateOf(false)} // -> STATE
                    IconButton(
                        onClick = { showSearchAndFilters = !showSearchAndFilters }
                    ){
                        Icon(painter = painterResource(R.drawable.search), "searchTopBar", tint = DarkGrey)
                    }
                    if(showSearchAndFilters){
                        SearchAndFilters() // -> SHOW HERE
                    }
                }
            )
        }
    )

}


@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItems.Home,
        NavigationItems.Books,
        NavigationItems.Camera,
        NavigationItems.Bookmarks,
        NavigationItems.Profile,
    )
    BottomNavigation(
        backgroundColor = DarkGrey,
        modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 14.dp)
            .clip(RoundedCornerShape(percent = 30))
            .height(60.dp)

    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { items ->
            BottomNavigationItem(
                icon = { Icon(painter = painterResource(items.icon), items.title, modifier = Modifier.height(28.dp), tint = LightGreyText)},
                selectedContentColor = LightGreyText,
                unselectedContentColor = LightGreyText,
                selected = currentRoute == items.route,
                onClick = {
                    navController.navigate(items.route){
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    when (navBackStackEntry?.destination?.route) {
        "home" -> {
            rememberSaveable { (mutableStateOf(true)) }.value = true
        }
        "books" -> {
            rememberSaveable { (mutableStateOf(true)) }.value = true
        }
        "camera" -> {
            rememberSaveable { (mutableStateOf(true)) }.value = false
        }
        "bookmarks" -> {
            rememberSaveable { (mutableStateOf(true)) }.value = false
        }
        "profile" -> {
            rememberSaveable { (mutableStateOf(true)) }.value = false
        }
    }
    Scaffold(
        topBar = { TopBar(navController, rememberSaveable { (mutableStateOf(true)) }) },
        content = { padding ->
                  Column(modifier = Modifier.padding(top = 26.dp)){
                      Navigation(navController)
                  }
        },
        bottomBar = { BottomNavigationBar(navController) },
    )
}





// перетащить навигацию
@Composable
fun Navigation(navController: NavHostController) {

    NavHost(navController, NavigationItems.Home.route) {
        composable(NavigationItems.Home.route) {
            HomeScreen()
        }
        composable(NavigationItems.Books.route) {
            BooksScreen()
        }
        composable(NavigationItems.Camera.route) {
            CameraPreview()
        }
        composable(NavigationItems.Bookmarks.route) {
            BookmarksScreen()
        }
        composable(NavigationItems.Profile.route) {
            ProfileScreen()
        }
    }

}


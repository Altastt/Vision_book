package com.example.myapplication

import CameraPreview
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
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
import java.util.concurrent.ExecutorService

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()

        }
    }

}



// States
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@SuppressLint
@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(
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
        // поменять стиль по минимуму
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
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        content = { padding ->
                  Column(modifier = Modifier.padding(top = 30.dp)){
                      Navigation(navController)
                  }
        },
        topBar = { TopBar() },
        bottomBar = { BottomNavigationBar(navController) }
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


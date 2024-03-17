package com.example.myapplication

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myapplication.itemsOfScreen.SearchAndFilters
import com.example.myapplication.ui.theme.DarkGrey
import com.example.myapplication.ui.theme.sourceSans


@Composable
fun AnimatedTopNavigationBar(
    navController: NavController,
    topAppBarState: MutableState<Boolean>
) {
    AnimatedVisibility(
        visible = topAppBarState.value,
        enter = slideInVertically(initialOffsetY = { -it }),
        exit = slideOutVertically(targetOffsetY = { -it })
    ) {
        TopBar(navController)
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint
@Composable
fun TopBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val title: String = when (navBackStackEntry?.destination?.route ?: "home") {
        "home" -> "Home"
        "books" -> "Books"
        "camera" -> "Camera"
        "bookmarks" -> "Bookmarks"
        "profile" -> "Profile"
        else -> "Home"
    }
            TopAppBar(
                title = { Text(
                    stringResource(R.string.topbar_agitation),
                    modifier = Modifier.padding(start = 12.dp, end = 20.dp),
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontFamily = sourceSans,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp)
                )
                },
                actions = {
                    var showSearchAndFilters by remember { mutableStateOf(false) } // -> STATE
                    IconButton(
                        onClick = { showSearchAndFilters = !showSearchAndFilters }
                    ){
                        Icon(painter = painterResource(R.drawable.search), "searchTopBar", tint = MaterialTheme.colorScheme.primary)
                    }
                    if(showSearchAndFilters){
                        SearchAndFilters()
                    }
                }
            )
}
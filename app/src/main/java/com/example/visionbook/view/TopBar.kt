package com.example.visionbook.view

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.visionbook.ui.theme.sourceSans
import com.example.visionbook.viewmodels.SearchAndFiltersVM
import com.example.visionbook.R



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimatedTopNavigationBar(
    navController: NavController,
    topAppBarState: MutableState<Boolean>,
    scrollBehavior: TopAppBarScrollBehavior,
    viewModel: SearchAndFiltersVM
) {
    AnimatedVisibility(
        visible = topAppBarState.value,
        enter = slideInVertically(initialOffsetY = { -it }),
        exit = slideOutVertically(targetOffsetY = { -it })
    ) {
        TopBar(navController, scrollBehavior, viewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint
@Composable
fun TopBar(navController: NavController, scrollBehavior: TopAppBarScrollBehavior, viewModel: SearchAndFiltersVM) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
   // val gradientColors = listOf(Color(0xFFEC723D), Color(0xFFE9A986), Color(0xFFEFEBE7))
    val title: String = when (navBackStackEntry?.destination?.route ?: "home") {
        "home" -> "Home"
        "books" -> "Books"
        "camerainprofile" -> "CameraInProfile"
        "bookmarks" -> "Bookmarks"
        "profile" -> "Profile"
        else -> "Home"
    }
    TopAppBar(
        title = {
            Text(
                stringResource(R.string.topbar_agitation),
                modifier = Modifier.padding(start = 12.dp, end = 20.dp),
                style = TextStyle(
                    fontFamily = sourceSans,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                  //  brush = Brush.linearGradient(
                        //colors = gradientColors
                    //)
                )
            )
        },
        actions = {
            IconButton(
                onClick = { viewModel.toggleSearchAndFilters() }
            ) {
                Icon(painter = painterResource(R.drawable.search), "searchTopBar")
            }

        },
        scrollBehavior = scrollBehavior
    )
}
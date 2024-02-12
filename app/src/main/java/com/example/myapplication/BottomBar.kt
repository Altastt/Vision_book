package com.example.myapplication

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myapplication.models.NavigationItems
import com.example.myapplication.ui.theme.DarkGrey
import com.example.myapplication.ui.theme.LightGreyText

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
                icon = { Icon(painter = painterResource(items.icon), items.title, modifier = Modifier.height(28.dp), tint = LightGreyText) },
                selectedContentColor = LightGreyText,
                unselectedContentColor = LightGreyText,
                selected = currentRoute == items.route,
                onClick = {
                    navController.navigate(items.route){
                        popUpTo(navController.graph.startDestinationRoute!!) { inclusive = false }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myapplication.models.NavigationItems

@Composable
fun AnimatedBottomNavigationBar(
    navController: NavController,
    bottomAppBarState: MutableState<Boolean>
) {
    AnimatedVisibility(
        visible = bottomAppBarState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it })
    ) {
        BottomNavigationBar(navController)
    }
}

@Composable
private fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItems.Home,
        NavigationItems.Books,
        NavigationItems.Camera,
        NavigationItems.Bookmarks,
        NavigationItems.Profile,
    )
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.onSecondary,
        modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 14.dp)
            .clip(RoundedCornerShape(percent = 30))
            .height(60.dp)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { items ->
            NavigationBarItem(
                icon = {
                    val colorOfItemNavBar = if (currentRoute == items.route) MaterialTheme.colorScheme.primaryContainer
                    else MaterialTheme.colorScheme.onSecondaryContainer
                    Icon(
                        painter = painterResource(items.icon),
                        contentDescription = items.title,
                        tint = colorOfItemNavBar,
                        modifier = Modifier.size(28.dp)
                    )
                },
                selected = currentRoute == items.route,
                onClick = {
                    navController.navigate(items.route) {
                        popUpTo(navController.graph.startDestinationRoute!!) { inclusive = false }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

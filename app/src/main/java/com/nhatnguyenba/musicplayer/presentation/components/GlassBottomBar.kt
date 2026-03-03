package com.nhatnguyenba.musicplayer.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun GlassBottomBar(modifier: Modifier, navController: NavHostController) {

    val items = listOf(
        Screen.Home,
        Screen.Search,
        Screen.Library,
        Screen.Profile
    )

    val currentRoute =
        navController.currentBackStackEntryAsState().value?.destination?.route

    Box(
        modifier = modifier
            .padding(20.dp)
            .clip(RoundedCornerShape(40.dp))
            .background(Color.White.copy(alpha = 0.08f))
            .fillMaxWidth()
            .height(70.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            items.forEach { screen ->

                val selected = currentRoute == screen.route

                BottomItem(
                    screen = screen,
                    selected = selected,
                    onClick = {
                        navController.navigate(screen.route) {

                            // tránh tạo nhiều bản copy màn
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }

                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}

sealed class Screen(
    val route: String,
    val icon: ImageVector,
    val label: String
) {
    object Home : Screen("home", Icons.Default.Home, "Home")
    object Search : Screen("search", Icons.Default.Search, "Search")
    object Library : Screen("library", Icons.Default.LibraryMusic, "Library")
    object Profile : Screen("profile", Icons.Default.Person, "Profile")
}

@Preview(showBackground = true)
@Composable
fun GlassBottomBarPreview() {
    val navController = rememberNavController()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(bottom = 16.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        GlassBottomBar(Modifier.align(Alignment.BottomCenter), navController = navController)
    }
}
package com.nhatnguyenba.musicplayer.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nhatnguyenba.musicplayer.presentation.components.GlassBottomBar
import com.nhatnguyenba.musicplayer.presentation.components.Screen
import com.nhatnguyenba.musicplayer.presentation.home.HomeScreen
import com.nhatnguyenba.musicplayer.presentation.library.LibraryScreen
import com.nhatnguyenba.musicplayer.presentation.profile.ProfileScreen
import com.nhatnguyenba.musicplayer.presentation.search.SearchScreen
import com.nhatnguyenba.musicplayer.presentation.theme.MusicPlayerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                scrim = Color.Transparent.toArgb(),
                darkScrim = Color.Transparent.toArgb()
            ),
            navigationBarStyle = SystemBarStyle.light(
                scrim = Color.Transparent.toArgb(),
                darkScrim = Color.Transparent.toArgb()
            )
        )

        setContent {
            MusicPlayerTheme {
                MusicApp()
            }
        }
    }
}

@Composable
fun MusicApp() {

    val navController = rememberNavController()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
        ) {

            composable(Screen.Home.route) { HomeScreen() }
            composable(Screen.Search.route) { SearchScreen() }
            composable(Screen.Library.route) { LibraryScreen() }
            composable(Screen.Profile.route) { ProfileScreen() }
        }

        GlassBottomBar(modifier = Modifier.align(Alignment.BottomCenter), navController)
    }
}
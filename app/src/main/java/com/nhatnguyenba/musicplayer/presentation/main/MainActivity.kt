package com.nhatnguyenba.musicplayer.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nhatnguyenba.musicplayer.presentation.components.GlassBottomBar
import com.nhatnguyenba.musicplayer.presentation.components.Screen
import com.nhatnguyenba.musicplayer.presentation.home.HomeScreen
import com.nhatnguyenba.musicplayer.presentation.home.HomeViewModel
import com.nhatnguyenba.musicplayer.presentation.library.LibraryScreen
import com.nhatnguyenba.musicplayer.presentation.nowplaying.PlayerViewModel
import com.nhatnguyenba.musicplayer.presentation.nowplaying.PlayingScreen
import com.nhatnguyenba.musicplayer.presentation.profile.ProfileScreen
import com.nhatnguyenba.musicplayer.presentation.search.SearchScreen
import com.nhatnguyenba.musicplayer.presentation.search.SearchViewModel
import com.nhatnguyenba.musicplayer.presentation.theme.MusicPlayerTheme
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.rememberHazeState

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MusicPlayerTheme {
                val view = LocalView.current

                SideEffect {
                    val window = this.window ?: return@SideEffect
                    val insetsController = WindowInsetsControllerCompat(window, view)

                    insetsController.isAppearanceLightStatusBars = false
                    insetsController.isAppearanceLightNavigationBars = false

                    window.statusBarColor = Color.Transparent.toArgb()
                    window.navigationBarColor = Color.Transparent.toArgb()
                }

                MusicApp()
            }
        }
    }
}

@Composable
fun MusicApp() {
    val navController = rememberNavController()
    val hazeState = rememberHazeState()
    val homeViewModel: HomeViewModel = hiltViewModel()
    val searchViewModel: SearchViewModel = hiltViewModel()
    val playerViewModel: PlayerViewModel = hiltViewModel()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier
                .fillMaxSize()
                .hazeSource(state = hazeState)
        ) {

            composable(Screen.Home.route) {
                HomeScreen(
                    viewModel = homeViewModel,
                    hazeState = hazeState
                )
            }
            composable(Screen.Search.route) {
                SearchScreen(
                    playerViewModel = playerViewModel,
                    searchViewModel = searchViewModel,
                    navController = navController
                )
            }
            composable(Screen.Library.route) { LibraryScreen() }
            composable(Screen.Profile.route) { ProfileScreen() }
            composable(Screen.Playing.route) {
                PlayingScreen(
                    navController = navController,
                    playerViewModel = playerViewModel
                )
            }
        }

        if (currentRoute != Screen.Playing.route) {
            GlassBottomBar(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(20.dp)
                    .clip(RoundedCornerShape(40.dp))
                    .hazeEffect(
                        state = hazeState,
                        style = HazeStyle(
                            blurRadius = 20.dp,
                            backgroundColor = Color.White.copy(alpha = 0.09f),
                            tint = HazeTint(Color.White.copy(alpha = 0.2f))
                        )
                    ),
                navController
            )
        }
    }
}
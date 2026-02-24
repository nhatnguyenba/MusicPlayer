package com.nhatnguyenba.musicplayer.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.nhatnguyenba.musicplayer.presentation.home.MusicHomeScreen
import com.nhatnguyenba.musicplayer.presentation.theme.MusicPlayerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MusicPlayerTheme {
                MusicHomeScreen()
            }
        }
    }
}
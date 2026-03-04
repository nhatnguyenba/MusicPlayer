package com.nhatnguyenba.musicplayer.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontVariation.weight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nhatnguyenba.musicplayer.presentation.home.HomeUiState
import com.nhatnguyenba.musicplayer.presentation.home.HomeViewModel

@Composable
fun TopDailySection(
    viewModel: HomeViewModel = hiltViewModel()
) {

    val state by viewModel.uiState.collectAsState()

    Column {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "Top daily playlists",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = "See all",
                color = Color.Gray
            )
        }

        Spacer(Modifier.height(20.dp))

        when (state) {

            is HomeUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is HomeUiState.Success -> {
                val playlists = (state as HomeUiState.Success).playlists
                repeat(playlists.size) { index ->
                    PlaylistItem(playlists[index])
                    Spacer(Modifier.height(16.dp))
                }
            }

            is HomeUiState.Error -> {
                Text(
                    text = (state as HomeUiState.Error).message,
                    color = Color.Red
                )
            }
        }
    }
}
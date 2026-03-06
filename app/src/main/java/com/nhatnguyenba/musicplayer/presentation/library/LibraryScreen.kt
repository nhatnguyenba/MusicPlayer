package com.nhatnguyenba.musicplayer.presentation.library

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.nhatnguyenba.musicplayer.R
import com.nhatnguyenba.musicplayer.domain.models.Song
import com.nhatnguyenba.musicplayer.presentation.common.UiState
import com.nhatnguyenba.musicplayer.presentation.components.FilterChipSection
import com.nhatnguyenba.musicplayer.presentation.components.Screen
import com.nhatnguyenba.musicplayer.presentation.nowplaying.PlayerViewModel
import com.nhatnguyenba.musicplayer.presentation.search.SearchResult

@Composable
fun LibraryScreen(
    libraryViewModel: LibraryViewModel,
    playerViewModel: PlayerViewModel,
    navController: NavController
) {

    val selectedFilter by libraryViewModel.currentFilter.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0E0E0E))
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(horizontal = 16.dp)
    ) {

        LibraryTopBar()

        FilterChipSection(
            filters = LibraryFilter.entries.toList(),
            selectedFilter = selectedFilter,
            onFilterSelected = {
                libraryViewModel.onFilterChange(it)
            },
            label = { it.title },
            modifier = Modifier
        )

        LibraryList(
            libraryViewModel = libraryViewModel,
            playerViewModel = playerViewModel,
            navController = navController
        )
    }
}

@Composable
fun LibraryTopBar() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        AsyncImage(
            model = "https://i.pravatar.cc/150?img=5",
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        )

        Spacer(Modifier.width(12.dp))

        Text(
            text = "Your Library",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )

        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.padding(end = 16.dp)
        )

        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = null,
            tint = Color.White
        )
    }
}

@Composable
fun LibraryList(
    libraryViewModel: LibraryViewModel,
    playerViewModel: PlayerViewModel,
    navController: NavController
) {
    val uiState by libraryViewModel.uiState.collectAsState()

    LazyColumn {

        when (val state = uiState) {

            is UiState.Loading -> {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }

            is UiState.Error -> {
                item {
                    Text(
                        text = state.message,
                        color = Color.Red
                    )
                }
            }

            is UiState.Success -> {

                when (val result = state.data) {

                    is SearchResult.Genres -> {

                    }

                    is SearchResult.Songs -> {
                        items(result.songs.size) { index ->
                            LibrarySongItem(
                                playerViewModel = playerViewModel,
                                result.songs[index],
                                navController
                            )
                        }
                    }

                    is SearchResult.Artists -> {

                    }

                    is SearchResult.Albums -> {

                    }

                    is SearchResult.Playlists -> {

                    }
                }
            }
        }
    }
}

@Composable
fun LibrarySongItem(playerViewModel: PlayerViewModel, song: Song, navController: NavController) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .clickable {
                playerViewModel.playSong(song)
                navController.navigate(Screen.Playing.route)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {

        AsyncImage(
            model = song.imageUrl,
            contentDescription = song.title,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(R.drawable.ic_music),
            error = painterResource(R.drawable.ic_music),
            modifier = Modifier
                .size(60.dp)
                .clip(
                    RoundedCornerShape(8.dp)
                )
        )

        Spacer(Modifier.width(12.dp))

        Column {

            Text(
                text = song.title,
                color = Color.White,
                fontSize = 16.sp,
                maxLines = 1
            )

            Text(
                text = song.artist,
                color = Color.Gray,
                fontSize = 14.sp
            )
        }
    }
}
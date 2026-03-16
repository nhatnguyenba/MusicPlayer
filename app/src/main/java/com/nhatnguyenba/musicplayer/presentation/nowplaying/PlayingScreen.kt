package com.nhatnguyenba.musicplayer.presentation.nowplaying

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.QueueMusic
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.nhatnguyenba.musicplayer.domain.models.Song
import com.nhatnguyenba.musicplayer.presentation.components.MusicSlider
import com.nhatnguyenba.musicplayer.presentation.components.RotatingAlbumArt
import java.util.Locale

@Composable
fun PlayingScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    playerViewModel: PlayerViewModel = hiltViewModel()
) {
    val song by playerViewModel.currentSong.collectAsStateWithLifecycle()
    val isPlaying by playerViewModel.isPlaying.collectAsStateWithLifecycle()

    song?.let {
        Box(modifier = modifier.fillMaxSize()) {

            // Background image
            AsyncImage(
                model = it.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .blur(20.dp)
            )

            // Dark overlay
            Box(
                Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color(0x99000000),
                                Color(0xFF000000)
                            )
                        )
                    )
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
                    .statusBarsPadding()
                    .navigationBarsPadding(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                item {
                    TopBar(navController = navController)
                }

                item {
                    Spacer(Modifier.height(35.dp))
                }

                item {
                    RotatingAlbumArt(imageUrl = it.imageUrl, isPlaying = isPlaying)
                }

                item {
                    Spacer(Modifier.height(27.dp))
                }

                item {
                    SongInfo(it)
                }

                item {
                    Spacer(Modifier.height(24.dp))
                }

                item {
                    LyricsPreview()
                }

                item {
                    Spacer(Modifier.height(40.dp))
                }

                item {
                    ProgressSection(playerViewModel)
                }

                item {
                    Spacer(Modifier.height(27.dp))
                }

                item {
                    PlayerControls(playerViewModel)
                }
            }
        }
    }
}

@Composable
fun TopBar(navController: NavController) {

    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                Icons.Default.ArrowBack,
                contentDescription = null,
                tint = Color.White
            )
        }

        Spacer(Modifier.weight(1f))

        Text(
            "Now Playing",
            color = Color.White,
            fontSize = 18.sp
        )

        Spacer(Modifier.weight(1f))

        IconButton(onClick = {}) {
            Icon(
                Icons.Default.FavoriteBorder,
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}

@Composable
fun SongInfo(song: Song) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            song.title,
            fontSize = 26.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(Modifier.height(4.dp))

        Text(
            song.artist,
            fontSize = 16.sp,
            color = Color.White.copy(alpha = 0.7f),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun LyricsPreview() {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            "Whispers in the midnight breeze,",
            color = Color.White.copy(alpha = 0.4f)
        )

        Spacer(Modifier.height(6.dp))

        Text(
            "Carrying dreams across the seas,",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(Modifier.height(6.dp))

        Text(
            "I close my eyes, let go, and drift away.",
            color = Color.White.copy(alpha = 0.4f)
        )
    }
}

@Composable
fun ProgressSection(playerViewModel: PlayerViewModel) {
    val currentPosition by playerViewModel.currentPosition.collectAsStateWithLifecycle()
    val duration by playerViewModel.duration.collectAsStateWithLifecycle()
    val progress by playerViewModel.progress.collectAsStateWithLifecycle()

    Column {

        MusicSlider(
            progress = progress,
            onSeek = {
                playerViewModel.seekTo(it)
            }
        )

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(formatDuration(currentPosition), color = Color.White.copy(alpha = 0.6f))

            Text(formatDuration(duration), color = Color.White.copy(alpha = 0.6f))
        }
    }
}

fun formatDuration(ms: Long): String {
    val totalSeconds = ms / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return String.format(Locale.US, "%d:%02d", minutes, seconds)
}

@Composable
fun PlayerControls(playerViewModel: PlayerViewModel) {

    val isPlaying by playerViewModel.isPlaying.collectAsStateWithLifecycle()

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            Icons.Default.Shuffle,
            contentDescription = null,
            tint = Color.White
        )

        IconButton(onClick = {}) {
            Icon(
                Icons.Default.SkipPrevious,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(36.dp)
            )
        }

        Box(
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
                .background(Color(0xFFD6F36A)),
            contentAlignment = Alignment.Center
        ) {

            IconButton(
                onClick = { playerViewModel.onPlayPauseClick() }
            ) {

                Icon(
                    imageVector =
                        if (isPlaying) Icons.Default.Pause
                        else Icons.Default.PlayArrow,
                    contentDescription = null
                )
            }
        }

        IconButton(onClick = {}) {
            Icon(
                Icons.Default.SkipNext,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(36.dp)
            )
        }

        Icon(
            Icons.Default.QueueMusic,
            contentDescription = null,
            tint = Color.White
        )
    }
}
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.QueueMusic
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import coil.compose.AsyncImage
import com.nhatnguyenba.musicplayer.domain.models.Song

@Composable
fun PlayingScreen(
    modifier: Modifier = Modifier,
    playerViewModel: PlayerViewModel = hiltViewModel()
) {
    val song by playerViewModel.currentSong.collectAsState()

    song?.let {
        Box(modifier = modifier.fillMaxSize()) {

            // Background image
            AsyncImage(
                model = it.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .blur(80.dp)
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

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
                    .statusBarsPadding(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                TopBar()

                Spacer(Modifier.height(40.dp))

                Artwork(it.imageUrl)

                Spacer(Modifier.height(32.dp))

                SongInfo(it)

                Spacer(Modifier.height(24.dp))

                LyricsPreview()

                Spacer(Modifier.height(40.dp))

                ProgressSection()

                Spacer(Modifier.height(32.dp))

                PlayerControls()
            }
        }
    }
}

@Composable
fun TopBar() {

    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(onClick = {}) {
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
fun Artwork(imageUrl: String) {

    AsyncImage(
        model = imageUrl,
        contentDescription = null,
        modifier = Modifier
            .size(260.dp)
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )
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
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(4.dp))

        Text(
            song.artist,
            fontSize = 16.sp,
            color = Color.White.copy(alpha = 0.7f)
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
fun ProgressSection() {

    var progress by remember { mutableFloatStateOf(0.3f) }

    Column {

        Slider(
            value = progress,
            onValueChange = { progress = it },
            colors = SliderDefaults.colors(
                thumbColor = Color(0xFFD6F36A),
                activeTrackColor = Color(0xFFD6F36A)
            )
        )

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text("0:28", color = Color.White.copy(alpha = 0.6f))

            Text("-2:15", color = Color.White.copy(alpha = 0.6f))
        }
    }
}

@Composable
fun PlayerControls() {

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

            Icon(
                Icons.Default.Pause,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(36.dp)
            )
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
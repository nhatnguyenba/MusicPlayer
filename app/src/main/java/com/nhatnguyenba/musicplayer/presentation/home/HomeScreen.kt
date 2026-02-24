package com.nhatnguyenba.musicplayer.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nhatnguyenba.musicplayer.R
import com.nhatnguyenba.musicplayer.presentation.components.BottomNavigationBar
import com.nhatnguyenba.musicplayer.presentation.components.MiniPlayer

// Dữ liệu mẫu
data class Playlist(
    val id: Int,
    val title: String,
    val subtitle: String,
    val imageRes: Int
)

val samplePlaylists = listOf(
    Playlist(1, "Starlit Reverie", "Budiiarti x Lil magrib", R.drawable.placeholder),
    Playlist(2, "Midnight Confessions", "By Alexiao • 24 Songs", R.drawable.placeholder),
    Playlist(3, "Lost in the Echo", "By Alexiao • 24 Songs", R.drawable.placeholder),
    Playlist(4, "Letters I Never Sent", "By Alexiao • 24 Songs", R.drawable.placeholder),
    Playlist(5, "Breaking the Silence", "By Alexiao • 24 Songs", R.drawable.placeholder),
    Playlist(6, "Tears on the Vinyl", "By Alexiao • 24 Songs", R.drawable.placeholder)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicHomeScreen() {
    var selectedChip by remember { mutableStateOf("All") }
    val chips = listOf("All", "New Release", "Trending", "Top")
    var selectedNavItem by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Hi, Samantha",
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                    )
                },
                actions = {
                    IconButton(onClick = { /* TODO: Search */ }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                    IconButton(onClick = { /* TODO: Profile */ }) {
                        Icon(Icons.Default.AccountCircle, contentDescription = "Profile")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                )
            )
        },
        bottomBar = {
            Column {
                MiniPlayer(
                    songTitle = "Midnight Confessions",
                    artist = "Alexiao",
                    currentTime = "0:28",
                    totalTime = "-2:15",
                    isPlaying = true,
                    onPlayPause = { /* TODO: xử lý play/pause */ },
                    onPrevious = { /* TODO: previous */ },
                    onNext = { /* TODO: next */ }
                )
                BottomNavigationBar(
                    selectedItem = selectedNavItem,
                    onItemSelected = { selectedNavItem = it }
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Filter chips
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    chips.forEach { chip ->
                        FilterChip(
                            selected = selectedChip == chip,
                            onClick = { selectedChip = chip },
                            label = { Text(chip) },
                            modifier = Modifier.wrapContentWidth()
                        )
                    }
                }
            }

            // Curated & trending section
            item {
                SectionHeader(title = "Curated & trending")
            }

            // Discover weekly card
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Discover weekly",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "The original slow instrumental best playlists.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Image(
                            painter = painterResource(id = R.drawable.placeholder), // Thay bằng ảnh thật
                            contentDescription = null,
                            modifier = Modifier
                                .size(80.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }

            // Top daily playlists header
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Top daily playlists",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    TextButton(onClick = { /* TODO: See all */ }) {
                        Text("See all")
                    }
                }
            }

            // Danh sách playlist
            items(samplePlaylists.size) { index ->
                PlaylistItem(playlist = samplePlaylists[index])
            }
        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Composable
fun PlaylistItem(playlist: Playlist) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = playlist.imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = playlist.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = playlist.subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        IconButton(onClick = { /* TODO: Options */ }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_more_vert),
                contentDescription = "More options"
            )
        }
    }
}
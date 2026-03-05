package com.nhatnguyenba.musicplayer.presentation.search

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.nhatnguyenba.musicplayer.domain.models.Album
import com.nhatnguyenba.musicplayer.domain.models.Artist
import com.nhatnguyenba.musicplayer.domain.models.Genre
import com.nhatnguyenba.musicplayer.domain.models.Playlist
import com.nhatnguyenba.musicplayer.domain.models.Song
import com.nhatnguyenba.musicplayer.presentation.common.UiState
import com.nhatnguyenba.musicplayer.presentation.components.FilterChipSection
import com.nhatnguyenba.musicplayer.presentation.components.PlaylistItem
import com.nhatnguyenba.musicplayer.presentation.components.Screen
import com.nhatnguyenba.musicplayer.presentation.components.SearchBar
import com.nhatnguyenba.musicplayer.presentation.nowplaying.PlayerViewModel

@Composable
fun SearchScreen(
    searchViewModel: SearchViewModel = hiltViewModel(),
    playerViewModel: PlayerViewModel = hiltViewModel(),
    bottomBarHeight: Dp = 80.dp,
    navController: NavController
) {

    val state by searchViewModel.uiState.collectAsState()

    var query by remember { mutableStateOf("") }
    var selectedFilter by remember {
        mutableStateOf(SearchFilter.SONGS)
    }

//    LaunchedEffect(selectedFilter) {
//        viewModel.onFilterChange(selectedFilter)
//    }

//    LaunchedEffect(query) {
//        viewModel.onQueryChange(query)
//    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0E0E0E))
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(horizontal = 16.dp)
    ) {

        Spacer(Modifier.height(12.dp))

        // Search Box
        SearchBar(
            query = query,
            onQueryChange = {
                query = it
                searchViewModel.onQueryChange(it)
            }
        )

        Spacer(Modifier.height(16.dp))

        if (query.isNotEmpty()) {

            FilterChipSection(
                filters = SearchFilter.entries.toList(),
                selectedFilter = selectedFilter,
                onFilterSelected = {
                    selectedFilter = it
                    searchViewModel.onFilterChange(it)
                },
                label = { it.title },
                modifier = Modifier
            )

            Spacer(Modifier.height(16.dp))

            SearchResultList(
                playerViewModel = playerViewModel,
                state = state,
                navController = navController
            )
        } else {
            BrowseSection(state, bottomBarHeight) // grid browse khi chưa search
        }
    }
}

@Composable
fun BrowseSection(state: UiState<SearchResult>, bottomBarHeight: Dp = 80.dp) {

    when (state) {

        is UiState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is UiState.Success -> {
            val categories = (state.data as SearchResult.Genres).genres
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    bottom = bottomBarHeight + 16.dp
                )
            ) {
                items(categories.size) { index ->
                    CategoryCard(categories[index])
                }
            }
        }

        is UiState.Error -> {
            Text(
                text = state.message,
                color = Color.Red
            )
        }
    }
}

@Composable
fun CategoryCard(genre: Genre) {

    val randomColor = listOf(
        Color(0xFF1E3264),
        Color(0xFF8D67AB),
        Color(0xFFBA5D07),
        Color(0xFF27856A),
        Color(0xFF503750),
        Color(0xFF148A08)
    ).random()

    Box(
        modifier = Modifier
            .height(120.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(randomColor)
            .padding(16.dp)
    ) {

        Text(
            text = genre.name,
            color = Color.White,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 2,
            modifier = Modifier
                .align(Alignment.TopStart)
                .fillMaxWidth(0.7f)
        )

        AsyncImage(
            model = genre.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(90.dp)
                .align(Alignment.BottomEnd)
                .offset(x = 40.dp, y = 10.dp)
                .rotate(25f)
                .clip(RoundedCornerShape(12.dp))
        )
    }
}

@Composable
fun SearchResultList(
    playerViewModel: PlayerViewModel,
    state: UiState<SearchResult>,
    navController: NavController
) {

    LazyColumn(
        contentPadding = PaddingValues(bottom = 140.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        when (state) {

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
                            SongItem(
                                playerViewModel = playerViewModel,
                                result.songs[index],
                                navController
                            )
                        }
                    }

                    is SearchResult.Artists -> {
                        items(result.artists.size) { index ->
                            ArtistItem(result.artists[index])
                        }
                    }

                    is SearchResult.Albums -> {
                        items(result.albums.size) { index ->
                            AlbumItem(result.albums[index])
                        }
                    }

                    is SearchResult.Playlists -> {
                        items(result.playlists.size) { index ->
                            PlaylistItem(result.playlists[index])
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SongItem(
    playerViewModel: PlayerViewModel = hiltViewModel(),
    song: Song,
    navController: NavController,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                playerViewModel.playSong(song)
                navController.navigate(Screen.Playing.route)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {

        AsyncImage(
            model = song.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(song.title, color = Color.White)
            Text(song.artist, color = Color.Gray, fontSize = 13.sp)
        }

        Icon(Icons.Default.MoreVert, null, tint = Color.Gray)
        Spacer(Modifier.width(8.dp))
        Icon(Icons.Default.Add, null, tint = Color.Gray)
    }
}

@Composable
fun ArtistItem(artist: Artist) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        AsyncImage(
            model = artist.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(Modifier.width(16.dp))

        Text(
            text = artist.name,
            color = Color.White,
            modifier = Modifier.weight(1f)
        )

        OutlinedButton(
            onClick = { },
            border = BorderStroke(1.dp, Color.Gray),
            shape = RoundedCornerShape(50)
        ) {
            Text("Follow", color = Color.White)
        }
    }
}

@Composable
fun AlbumItem(album: Album) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        AsyncImage(
            model = album.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(album.title, color = Color.White)
            Text(album.artist, color = Color.Gray)
        }

        Icon(Icons.Default.Add, null, tint = Color.Gray)
    }
}

@Composable
fun PlaylistItem(playlist: Playlist) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        AsyncImage(
            model = playlist.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(playlist.title, color = Color.White)
            Text(playlist.author, color = Color.Gray)
        }

        Icon(Icons.Default.Add, null, tint = Color.Gray)
    }
}

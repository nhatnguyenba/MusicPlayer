package com.nhatnguyenba.musicplayer.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.nhatnguyenba.musicplayer.domain.models.Genre
import com.nhatnguyenba.musicplayer.presentation.components.FilterChipsSection
import com.nhatnguyenba.musicplayer.presentation.components.SearchBar
import com.nhatnguyenba.musicplayer.presentation.home.HomeUiState

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    bottomBarHeight: Dp = 80.dp
) {

    val state by viewModel.uiState.collectAsState()

    var query by remember { mutableStateOf("") }

    val filters = listOf("Songs", "Artists", "Albums", "Playlists")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF2A1F40),
                        Color(0xFF0D0D16),
                        Color.Black
                    )
                )
            )
            .padding(horizontal = 16.dp)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {

        Spacer(Modifier.height(20.dp))

        // Search Box
        SearchBar(
            query = query,
            onQueryChange = { query = it }
        )

        Spacer(Modifier.height(20.dp))

        if (query.isNotEmpty()) {

            // Filter Chips (chỉ hiện khi có text)
            FilterChipsSection(filters)

            Spacer(Modifier.height(16.dp))

            Text(
                text = "Search results for \"$query\"",
                color = Color.White,
                style = MaterialTheme.typography.titleMedium
            )

        } else {

            // Browse Section
            Text(
                text = "Browse all",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(Modifier.height(16.dp))

            BrowseGrid(state, bottomBarHeight)
        }
    }
}

@Composable
fun BrowseGrid(state: SearchUiState, bottomBarHeight: Dp = 80.dp) {

    when (state) {

        is SearchUiState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is SearchUiState.Success -> {
            val categories = state.genres
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

        is SearchUiState.Error -> {
            Text(
                text = (state as HomeUiState.Error).message,
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

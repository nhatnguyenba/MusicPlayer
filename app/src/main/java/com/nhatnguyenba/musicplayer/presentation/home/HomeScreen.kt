package com.nhatnguyenba.musicplayer.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nhatnguyenba.musicplayer.presentation.components.CuratedSection
import com.nhatnguyenba.musicplayer.presentation.components.FilterChipSection
import com.nhatnguyenba.musicplayer.presentation.components.HeaderSection
import com.nhatnguyenba.musicplayer.presentation.components.TopDailySection
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeSource


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    hazeState: HazeState
) {
    var selectedFilter by remember {
        mutableStateOf(HomeFilter.ALL)
    }

    Box(
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
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .hazeSource(hazeState)
                .padding(horizontal = 20.dp),
            contentPadding = PaddingValues(bottom = 120.dp)
        ) {

            item { Spacer(Modifier.height(60.dp)) }

            item { HeaderSection() }

            item { Spacer(Modifier.height(24.dp)) }

            item {
                FilterChipSection(
                    filters = HomeFilter.entries.toList(),
                    selectedFilter = selectedFilter,
                    onFilterSelected = { selectedFilter = it },
                    label = { it.title },
                    modifier = Modifier
                )
            }

            item { Spacer(Modifier.height(32.dp)) }

            item { CuratedSection() }

            item { Spacer(Modifier.height(32.dp)) }

            item { TopDailySection(viewModel) }
        }
    }
}

enum class HomeFilter(val title: String) {
    ALL("All"),
    NEW("New Release"),
    TRENDING("Trending"),
    TOP("Top")
}
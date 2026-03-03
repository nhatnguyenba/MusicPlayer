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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nhatnguyenba.musicplayer.presentation.components.CuratedSection
import com.nhatnguyenba.musicplayer.presentation.components.FilterChipsSection
import com.nhatnguyenba.musicplayer.presentation.components.HeaderSection
import com.nhatnguyenba.musicplayer.presentation.components.TopDailySection


@Composable
fun HomeScreen() {

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
                .padding(horizontal = 20.dp),
            contentPadding = PaddingValues(bottom = 120.dp)
        ) {

            item { Spacer(Modifier.height(60.dp)) }

            item { HeaderSection() }

            item { Spacer(Modifier.height(24.dp)) }

            item { FilterChipsSection() }

            item { Spacer(Modifier.height(32.dp)) }

            item { CuratedSection() }

            item { Spacer(Modifier.height(32.dp)) }

            item { TopDailySection() }
        }
    }
}
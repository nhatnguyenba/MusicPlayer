package com.nhatnguyenba.musicplayer.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun <T> FilterChipSection(
    filters: List<T>,
    selectedFilter: T,
    onFilterSelected: (T) -> Unit,
    label: (T) -> String,
    modifier: Modifier
) {

    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        items(filters.size) { index ->

            val isSelected = filters[index] == selectedFilter

            FilterChipItemUI(
                title = label(filters[index]),
                isSelected = isSelected,
                onClick = { onFilterSelected(filters[index]) }
            )
        }
    }
}

@Composable
fun FilterChipItemUI(
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .padding(end = 12.dp)
            .clip(RoundedCornerShape(50))
            .background(
                if (isSelected)
                    Color(0xFFD7F26C)
                else
                    Color.White.copy(alpha = 0.08f)
            )
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        Text(
            text = title,
            color = if (isSelected) Color.Black else Color.White
        )
    }
}
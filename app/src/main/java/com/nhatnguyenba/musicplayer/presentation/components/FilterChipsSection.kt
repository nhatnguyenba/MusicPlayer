package com.nhatnguyenba.musicplayer.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FilterChipsSection(chips: List<String>) {

    var selected by remember { mutableStateOf("All") }

    LazyRow {

        items(chips.size) { index ->

            val isSelected = chips[index] == selected

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
                    .clickable { selected = chips[index] }
                    .padding(horizontal = 20.dp, vertical = 10.dp)
            ) {
                Text(
                    text = chips[index],
                    color = if (isSelected) Color.Black else Color.White
                )
            }
        }
    }
}
package com.nhatnguyenba.musicplayer.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BottomItem(
    screen: Screen,
    selected: Boolean,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .size(50.dp)
            .clip(CircleShape)
            .background(
                if (selected)
                    Color(0xFFD7F26C)
                else
                    Color.Transparent
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {

        Icon(
            imageVector = screen.icon,
            contentDescription = screen.label,
            tint = if (selected) Color.Black else Color.White
        )
    }
}
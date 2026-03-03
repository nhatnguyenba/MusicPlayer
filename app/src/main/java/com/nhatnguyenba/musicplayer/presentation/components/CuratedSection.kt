package com.nhatnguyenba.musicplayer.presentation.components

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun CuratedSection() {

    Column {

        Text(
            text = "Curated & trending",
            color = Color.White,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(28.dp))
                .background(Color(0xFFD8B4FE))
        ) {

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column(
                    verticalArrangement = Arrangement.SpaceBetween
                ) {

                    Column {
                        Text(
                            "Discover weekly",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )

                        Spacer(Modifier.height(8.dp))

                        Text(
                            "The original slow instrumental best playlists.",
                            color = Color.Black.copy(alpha = 0.7f),
                            fontSize = 14.sp
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {

                        FloatingActionButton(
                            onClick = {},
                            containerColor = Color(0xFF6B21A8),
                            modifier = Modifier.size(48.dp)
                        ) {
                            Icon(Icons.Default.PlayArrow, null)
                        }

                        Spacer(Modifier.width(16.dp))
                        Icon(Icons.Default.FavoriteBorder, null)
                        Spacer(Modifier.width(16.dp))
                        Icon(Icons.Default.Download, null)
                        Spacer(Modifier.width(16.dp))
                        Icon(Icons.Default.MoreHoriz, null)
                    }
                }

                AsyncImage(
                    model = "https://images.unsplash.com/photo-1511367461989-f85a21fda167",
                    contentDescription = null,
                    modifier = Modifier
                        .size(140.dp)
                        .clip(RoundedCornerShape(20.dp))
                )
            }
        }
    }
}
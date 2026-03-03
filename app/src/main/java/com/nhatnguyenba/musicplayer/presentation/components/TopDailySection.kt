package com.nhatnguyenba.musicplayer.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TopDailySection() {

    Column {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "Top daily playlists",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = "See all",
                color = Color.Gray
            )
        }

        Spacer(Modifier.height(20.dp))

        repeat(3) {
            PlaylistItem()
            Spacer(Modifier.height(16.dp))
        }
    }
}
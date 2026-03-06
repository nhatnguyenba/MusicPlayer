package com.nhatnguyenba.musicplayer.presentation.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun RotatingAlbumArt(
    imageUrl: String,
    isPlaying: Boolean,
    modifier: Modifier = Modifier
) {

    val rotation = remember { Animatable(0f) }

    LaunchedEffect(isPlaying) {

        if (isPlaying) {
            while (true) {

                rotation.animateTo(
                    targetValue = rotation.value + 360f,
                    animationSpec = tween(
                        durationMillis = 15000,
                        easing = LinearEasing
                    )
                )

                rotation.snapTo(rotation.value % 360)
            }
        } else {
            rotation.stop()
        }
    }

    AsyncImage(
        model = imageUrl,
        contentDescription = null,
        modifier = modifier
            .size(280.dp)
            .clip(CircleShape)
            .rotate(rotation.value)
    )
}
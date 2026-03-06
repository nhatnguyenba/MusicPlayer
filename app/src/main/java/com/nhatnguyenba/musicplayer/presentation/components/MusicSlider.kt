package com.nhatnguyenba.musicplayer.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun MusicSlider(
    progress: Float, // 0f -> 1f
    onSeek: (Float) -> Unit,
    modifier: Modifier = Modifier,
    trackHeight: Dp = 4.dp,
    thumbRadius: Dp = 8.dp,
    activeColor: Color = Color(0xFFD4FF4F),
    inactiveColor: Color = Color.Gray
) {

    var sliderWidth by remember { mutableStateOf(0f) }

    var isDragging by remember { mutableStateOf(false) }
    var dragProgress by remember { mutableFloatStateOf(progress) }

    val displayedProgress = if (isDragging) dragProgress else progress

    Box(
        modifier = modifier
            .height(32.dp)
            .fillMaxWidth()
            .pointerInput(Unit) {

                detectTapGestures { offset ->

                    val x = offset.x.coerceIn(0f, sliderWidth)
                    val newProgress = x / sliderWidth

                    onSeek(newProgress)
                }
            }
            .pointerInput(Unit) {

                detectDragGestures(

                    onDragStart = {
                        isDragging = true
                    },

                    onDragEnd = {
                        isDragging = false
                        onSeek(dragProgress)
                    },

                    onDragCancel = {
                        isDragging = false
                    }

                ) { change, _ ->

                    val x = change.position.x.coerceIn(0f, sliderWidth)
                    dragProgress = x / sliderWidth
                }
            }
    ) {

        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .onSizeChanged {
                    sliderWidth = it.width.toFloat()
                }
        ) {

            val trackY = size.height / 2
            val progressX = sliderWidth * displayedProgress

            // inactive track
            drawLine(
                color = inactiveColor,
                start = Offset(0f, trackY),
                end = Offset(sliderWidth, trackY),
                strokeWidth = trackHeight.toPx(),
                cap = StrokeCap.Round
            )

            // active track
            drawLine(
                color = activeColor,
                start = Offset(0f, trackY),
                end = Offset(progressX, trackY),
                strokeWidth = trackHeight.toPx(),
                cap = StrokeCap.Round
            )

            // thumb
            drawCircle(
                color = activeColor,
                radius = thumbRadius.toPx(),
                center = Offset(progressX, trackY)
            )
        }
    }
}
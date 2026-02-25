package com.example.jamble_challenge.core.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize

fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition(label = "Shimmer")
    val progress by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
        ),
        label = "ShimmerProgress"
    )

    if (size.width == 0) {
        Modifier.onGloballyPositioned { size = it.size }
    } else {
        val width = size.width.toFloat()
        val startOffsetX = (progress * 3 * width) - width 

        background(
            brush = Brush.linearGradient(
                colors = listOf(
                    Color(0xFFE9EBEF),
                    Color(0xFFF8F9FA),
                    Color(0xFFE9EBEF),
                ),
                start = Offset(startOffsetX, 0f),
                end = Offset(startOffsetX + width, size.height.toFloat())
            )
        )
            .onGloballyPositioned {
                size = it.size
            }
    }
}
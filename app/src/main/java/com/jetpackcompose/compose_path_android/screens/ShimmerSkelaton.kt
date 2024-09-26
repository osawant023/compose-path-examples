package com.jetpackcompose.compose_path_android.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.toSize

@Composable
fun rememberShimmerBrush(
    shimmerColors: List<Color> = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f),
    ),
    widthOfShadowBrush: Int = 500,
    angleOfAxisY: Float = 270f,
    durationMillis: Int = 1000,
): Brush {
    val transition = rememberInfiniteTransition()
    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = (widthOfShadowBrush + 100).toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = durationMillis,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    return Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(x = translateAnimation.value - widthOfShadowBrush, y = 0.0f),
        end = Offset(x = translateAnimation.value, y = angleOfAxisY),
        tileMode = TileMode.Clamp
    )
}

fun Modifier.shimmerEffect(
    shape: Shape = RectangleShape,
    shimmerBrush: Brush
): Modifier = composed {
    val density = LocalDensity.current
    var size by remember { mutableStateOf(androidx.compose.ui.geometry.Size.Zero) }

    Modifier
        .onSizeChanged { size = it.toSize() }
        .background(shimmerBrush, shape)
}

@Composable
fun ShimmerSkeleton(
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    content: @Composable () -> Unit,
    isLoading : Boolean = true ,
) {
    val shimmerBrush = rememberShimmerBrush()
    Box(modifier = modifier) {
        content()
        AnimatedVisibility(
            modifier = Modifier.matchParentSize(),
            visible = isLoading) {
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .matchParentSize()
                    .shimmerEffect(shape, shimmerBrush)
            )
        }
    }
}
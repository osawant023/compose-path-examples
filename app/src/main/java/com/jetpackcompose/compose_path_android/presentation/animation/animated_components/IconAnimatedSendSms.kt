/*
package com.billdesk.upiplugin.ui.core.ui.components.animated

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.billdesk.upiplugin.ui.R
import com.billdesk.upiplugin.ui.core.ui.theme.WhiteColor
import kotlin.math.roundToInt


@Composable
fun IconAnimatedSendSms(size: Dp = 100.dp) {
    val infiniteTransition = rememberInfiniteTransition()
    // Animation cycle for bg1 (slide + fade)
    val bg1Slide by infiniteTransition.animateFloat(
        initialValue = 200f,
        targetValue = -100f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500, easing = FastOutLinearInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val bg1Alpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500, easing = LinearEasing),
            initialStartOffset = StartOffset(200),
            repeatMode = RepeatMode.Restart
        )
    )


    val bg2Slide by infiniteTransition.animateFloat(
        initialValue = 200f,
        targetValue = -100f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500, delayMillis = 500, easing = FastOutLinearInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val bg2Alpha by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500, delayMillis = 500, easing = LinearEasing),
            initialStartOffset = StartOffset(200) ,
            repeatMode = RepeatMode.Restart
        )
    )


    val arrowOffsetX by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -100f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, delayMillis = 500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = Modifier
            .width(if (size < 100.dp) 100.dp else size)
            .aspectRatio(1f)
            .background(WhiteColor)
    ) {
        Box(
            Modifier.align(Alignment.Center)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize(0.8f)
                    .align(Alignment.Center)
                    .offset { IntOffset(bg1Slide.roundToInt(), 0) }
                    .alpha(bg1Alpha),
                imageVector = ImageVector.vectorResource(R.drawable.ic_send_sms_bg_1),
                contentDescription = "Verifying Icon",
                contentScale = ContentScale.Crop
            )
            Image(
                modifier = Modifier
                    .fillMaxSize(0.8f)
                    .align(Alignment.Center)
                    .offset { IntOffset(bg2Slide.roundToInt(), 0) }
                    .alpha(bg2Alpha),
                imageVector = ImageVector.vectorResource(R.drawable.ic_send_sms_bg_2),
                contentDescription = "Verifying Icon",
                contentScale = ContentScale.Crop
            )
            Image(
                modifier = Modifier
                    .fillMaxSize(0.8f)
                    .align(Alignment.Center)
                    .offset{
                        IntOffset(arrowOffsetX.roundToInt(), 0)
                    },
                imageVector = ImageVector.vectorResource(R.drawable.sending),
                contentDescription = "Verifying Icon",
                contentScale = ContentScale.Crop
            )
        }
    }
}


@Preview(device = "id:pixel_2", showBackground = true)
@Composable
private fun IconAnimatedSendSmsPreview() {
    Column(Modifier.fillMaxSize()) {
        IconAnimatedSendSms()
    }
}*/

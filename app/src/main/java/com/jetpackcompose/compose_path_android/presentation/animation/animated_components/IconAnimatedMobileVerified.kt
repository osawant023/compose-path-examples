/*
package com.jetpackcompose.compose_path_android.presentation.animation.animated_components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.billdesk.upiplugin.ui.R
import com.billdesk.upiplugin.ui.core.ui.theme.WhiteColor
import kotlinx.coroutines.delay


*/
/**
 * @param size size of the root composable [default/min = 100.dp]
 * *//*

@Composable
fun IconAnimatedMobileVerified(size: Dp = 200.dp) {

    var mobileSize by remember { mutableStateOf(IntSize(0, 0)) }
    var derivedOffset by remember { mutableStateOf(Rect(0f, 0f, 0f, 0f)) }

    var showTick by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (showTick) 1f else 0f,
        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing),
        label = "scaleAnim"
    )

    LaunchedEffect(Unit) {
        delay(1000)
        showTick = true
    }

    Box(
        modifier = Modifier
            .width(if (size < 100.dp) 100.dp else size)
            .aspectRatio(1f)
            .background(WhiteColor)
    )
    {
        Box(Modifier.align(Alignment.Center)) {
            Image(
                modifier = Modifier
                    .align(Alignment.Center)
                    .onPlaced {
                        mobileSize = it.size
                        derivedOffset = it.boundsInParent()
                    },
                imageVector = ImageVector.vectorResource(R.drawable.ic_mobile),
                contentDescription = "Verifying Icon"
            )
            Image(
                modifier = Modifier
                    .width(mobileSize.width.dp / 4f)
                    .align(Alignment.Center),
                imageVector = ImageVector.vectorResource(R.drawable.ic_bhim_upi),
                contentDescription = "Verifying Icon",
                contentScale = ContentScale.Crop
            )
        }
        val tickSize by remember {
            derivedStateOf {
                mobileSize.height / 5
            }
        }
        Icon(
            modifier = Modifier
                .size(tickSize.dp)
                .graphicsLayer(
                    translationX = derivedOffset.bottomRight.x + tickSize * 0.40f,
                    translationY = derivedOffset.bottomRight.y - tickSize * 0.80f,
                    scaleX = scale,
                    scaleY = scale
                ),
            imageVector = ImageVector.vectorResource(R.drawable.ic_mobile_verified_tick),
            contentDescription = "",
            tint = Color.Unspecified
        )
    }
}


@Preview(device = "id:pixel_2", showBackground = true)
@Composable
private fun IconVerifyMobileAnimated() {
    Column(Modifier.fillMaxSize()) {
        IconAnimatedMobileVerified()
    }
}*/

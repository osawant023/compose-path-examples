/*
package com.billdesk.upiplugin.ui.core.ui.components.animated

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
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


*/
/**
 * @param size size of the root composable [default/min = 100.dp]
* *//*

@Composable
fun IconAnimatedVerifyMobileNumber(size: Dp = 200.dp) {

    var mobileSize by remember { mutableStateOf(IntSize(0, 0)) }
    var derivedOffset by remember { mutableStateOf(Rect(0f,0f,0f,0f)) }

    val mTopRightX = derivedOffset.center.x
    val mTopRightY = derivedOffset.center.y - (mobileSize.height / 2f)

    val mCenterLeftX = derivedOffset.centerLeft.x
    val mCenterLeftY = derivedOffset.centerLeft.y

    val mBottomRightX = derivedOffset.bottomRight.x
    val mBottomRightY = derivedOffset.bottomRight.y

    val point1 = Offset(
        x = mTopRightX,
        y = mTopRightY
    )
    val point2 = Offset(
        x = mCenterLeftX,
        y = mCenterLeftY
    )
    val point3 = Offset(
        x = mBottomRightX,
        y = mBottomRightY
    )
    val offset = remember { Animatable(point3, Offset.VectorConverter) }

    LaunchedEffect(derivedOffset) {
        while (true) {
            offset.animateTo(point2, animationSpec = tween(durationMillis = 700))
            offset.animateTo(point3, animationSpec = tween(durationMillis = 700))
            offset.animateTo(point1, animationSpec = tween(durationMillis = 700))
        }
    }

    Box(modifier = Modifier
        .width(if (size < 100.dp) 100.dp else size)
        .aspectRatio(1f)
        .background(WhiteColor)
    ) {
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
                    .width((mobileSize.width / 4f).dp)
                    .align(Alignment.Center),
                imageVector = ImageVector.vectorResource(R.drawable.ic_bhim_upi),
                contentDescription = "Verifying Icon",
                contentScale = ContentScale.Crop
            )
        }
        Icon(
            modifier = Modifier
                .size((mobileSize.height / 4).dp)
                .graphicsLayer(
                    translationX = offset.value.x,
                    translationY = offset.value.y
                ),
            imageVector = ImageVector.vectorResource(R.drawable.ic_magnifying),
            contentDescription = "",
            tint = Color.Unspecified
        )
    }
}


@Preview(device = "id:pixel_2", showBackground = true)
@Composable
private fun IconVerifyMobileAnimated() {
    Column(Modifier.fillMaxWidth()) {
        IconAnimatedVerifyMobileNumber()
    }
}*/

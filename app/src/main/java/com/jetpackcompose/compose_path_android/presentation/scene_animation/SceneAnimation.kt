package com.jetpackcompose.compose_path_android.presentation.scene_animation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.SharedTransitionScope.ResizeMode.Companion.ScaleToBounds
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.jetpackcompose.compose_path_android.ui.theme.White

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SceneAnimation() {
    SharedTransitionLayout(
        Modifier.fillMaxSize()
    ) {
        var startSceneAnimation by remember { mutableStateOf(false) }
        Box(modifier = Modifier.fillMaxSize()) {
            AnimatedContent(
                startSceneAnimation,
            ) { targetState ->

                if (targetState) {
                    FullScreenDialog(
                        animatedVisibilityScope = this@AnimatedContent,
                        sharedTransitionScope = this@SharedTransitionLayout,
                        onDismiss = { startSceneAnimation = false }
                    )
                }else{
                    QRImageThumbnail(
                        animatedVisibilityScope = this@AnimatedContent,
                        sharedTransitionScope = this@SharedTransitionLayout
                    ) {
                        startSceneAnimation = true
                    }
                }
            }
        }

    }

}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun QRImageThumbnail(
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onClick: () -> Unit,
) {
    with(sharedTransitionScope) {
        Image(
            painter = rememberVectorPainter(Icons.Rounded.AccountBox),
            contentDescription = "QR Code",
            modifier = Modifier
                .size(100.dp)
                .clickable(onClick = onClick)
                .sharedBounds(
                    sharedContentState = rememberSharedContentState(key = "bounds"),
                    animatedVisibilityScope = animatedVisibilityScope,
                )
        )
    }

}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun FullScreenDialog(
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onDismiss: () -> Unit
) {
    with(sharedTransitionScope) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent.copy(alpha = 0.3f))
                .clickable(onClick = onDismiss)
        ) {

            Image(
                painter = rememberVectorPainter(Icons.Rounded.AccountBox),
                contentDescription = "Fullscreen QR",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxSize()
                    .aspectRatio(1f / 1f)
                    .sharedBounds(
                        sharedContentState = rememberSharedContentState(key = "bounds"),
                        animatedVisibilityScope = animatedVisibilityScope,
                    )
                    .background(color = White, shape = RoundedCornerShape(10.dp))
                    .padding(5.dp)
            )
        }
    }

}
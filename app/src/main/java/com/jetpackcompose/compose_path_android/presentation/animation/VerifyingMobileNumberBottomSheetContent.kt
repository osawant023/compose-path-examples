/*
package com.jetpackcompose.compose_path_android.presentation.animation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.billdesk.upiplugin.ui.core.ui.LocalSpacing
import com.billdesk.upiplugin.ui.core.ui.components.animated.IconAnimatedMobileVerified
import com.billdesk.upiplugin.ui.core.ui.components.animated.IconAnimatedSendSms
import com.billdesk.upiplugin.ui.core.ui.components.animated.IconAnimatedVerifyMobileNumber
import com.billdesk.upiplugin.ui.core.ui.theme.TextDarkColor
import com.billdesk.upiplugin.ui.core.ui.theme.Typography
import com.billdesk.upiplugin.ui.core.ui.theme.WhiteColor
import com.billdesk.upiplugin.ui.core.utils.VerifyMobileNoBottomSheetType
import com.jetpackcompose.compose_path_android.R
import com.jetpackcompose.compose_path_android.ui.theme.White
import kotlinx.coroutines.delay

enum class VerifyMobileNoBottomSheetType(val resStatus: Int){
    VERIFYING_MOBILE_NO(R.string.app_name),
    SENDING_SMS(R.string.app_name),
    MOBILE_VERIFIED(R.string.app_name)
}

@Composable
fun VerifyingMobileNumberBottomSheetContent(
    state: VerifyMobileNoBottomSheetType,
    onVerifySuccess: () -> Unit = {}
) {
    val spacing = 16.dp

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
            .padding(spacing),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedContent(
            targetState = state,
            transitionSpec = {
                when {
                    // VERIFYING → SENDING_SMS
                    initialState == VerifyMobileNoBottomSheetType.VERIFYING_MOBILE_NO &&
                            targetState == VerifyMobileNoBottomSheetType.SENDING_SMS -> {
                        (slideInHorizontally { it } + fadeIn()).togetherWith(slideOutHorizontally { -it } + fadeOut())

                    }
                    // SENDING_SMS → MOBILE_VERIFIED
                    initialState == VerifyMobileNoBottomSheetType.SENDING_SMS &&
                            targetState == VerifyMobileNoBottomSheetType.MOBILE_VERIFIED -> {
                        (scaleIn() + fadeIn()).togetherWith(scaleOut() + fadeOut())
                    }

                    else -> {
                        fadeIn().togetherWith(fadeOut())
                    }
                }.using(SizeTransform(clip = false))
            }
        ) { state ->

            when (state) {
                VerifyMobileNoBottomSheetType.VERIFYING_MOBILE_NO -> {
                    IconAnimatedVerifyMobileNumber(150.dp)
                }

                VerifyMobileNoBottomSheetType.SENDING_SMS -> {
                    IconAnimatedSendSms(150.dp)
                }

                VerifyMobileNoBottomSheetType.MOBILE_VERIFIED -> {
                    LaunchedEffect(Unit) {
                        delay(2 * 1000)
                        onVerifySuccess()
                    }
                    IconAnimatedMobileVerified(150.dp)
                }
            }

        }


        AnimatedContent(
            targetState = state,
            transitionSpec = {
                (fadeIn(tween(500)) + slideInVertically { it  })
                    .togetherWith(fadeOut(tween(500)) + slideOutVertically { -it })
            }
        ) { currentTextState ->
            Text(
                text = stringResource(currentTextState.resStatus),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 26.dp, bottom = spacing.spaceMedium
                    ),
                textAlign = TextAlign.Center,
                style = Typography.headlineMedium,
                color = TextDarkColor
            )
        }

    }
}

@Preview
@Composable
fun VerifyingMobileNumberBottomSheetContentPreview() {
    var uiState by remember { mutableStateOf(VerifyMobileNoBottomSheetType.VERIFYING_MOBILE_NO) }
    var play by remember { mutableStateOf(true) }
    LaunchedEffect(play) {
        delay(3 * 1000)
        uiState = VerifyMobileNoBottomSheetType.SENDING_SMS
        delay(3 * 1000)
        uiState = VerifyMobileNoBottomSheetType.MOBILE_VERIFIED
        delay(3 * 1000)
        uiState = VerifyMobileNoBottomSheetType.VERIFYING_MOBILE_NO
        play = play != true
    }
    VerifyingMobileNumberBottomSheetContent(uiState)
}*/

package com.jetpackcompose.compose_path_android.presentation.example_permission

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.jetpackcompose.compose_path_android.LocalPermissionManager
import com.jetpackcompose.compose_path_android.util.permission.AppPermission
import com.jetpackcompose.compose_path_android.util.permission.PermissionResult
import kotlinx.coroutines.delay


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun PermissionScreen() {
    val snackBarHostState = remember { SnackbarHostState() }
    val cs = rememberCoroutineScope()
    var snackMsg by remember { mutableStateOf("") }
    val context = LocalContext.current
    LaunchedEffect(snackMsg) {
        if (snackMsg.isEmpty()) return@LaunchedEffect
        snackBarHostState.showSnackbar(snackMsg)
        delay(1000)
        snackMsg = ""
    }
    val permissionManager = LocalPermissionManager.current
    var cameraPermissionResult by remember { mutableStateOf<PermissionResult?>(null) }
    LaunchedEffect(cameraPermissionResult == null) {
        permissionManager.request(AppPermission.Camera) { cameraPermissionResult = it }
    }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        }, modifier = Modifier.fillMaxSize()
    ) { padding ->
        Surface {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .scrollable(rememberScrollState(), orientation = Orientation.Horizontal)
                ) {

                    Button(
                        onClick = {
                            snackMsg = "Permission Granted"
                        }
                    ) {

                    }
                }
            }
        }
    }
}

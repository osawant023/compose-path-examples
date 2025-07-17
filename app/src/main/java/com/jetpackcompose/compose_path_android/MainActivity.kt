package com.jetpackcompose.compose_path_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jetpackcompose.compose_path_android.core.navigation.Route
import com.jetpackcompose.compose_path_android.presentation.document_scanner.DocumentScannerApp
import com.jetpackcompose.compose_path_android.presentation.encryption.UiScreenEncryption
import com.jetpackcompose.compose_path_android.presentation.scene_animation.SceneAnimation
import com.jetpackcompose.compose_path_android.presentation.start.ComposePathListScreen
import com.jetpackcompose.compose_path_android.ui.theme.ComposePathTheme
import com.jetpackcompose.compose_path_android.util.permission.BasePermissionBehavior
import com.jetpackcompose.compose_path_android.util.permission.PermissionManager

val LocalNavController = compositionLocalOf<NavHostController> { error("No Nav Controller Found") }
val LocalPermissionManager = compositionLocalOf<PermissionManager> { error("No Permission Manager") }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val permissionManager = PermissionManager(this, BasePermissionBehavior())
        enableEdgeToEdge()
        setContent {
            ComposePathTheme(dynamicColor = false) {
                val navController = rememberNavController()
                CompositionLocalProvider(
                    LocalNavController provides navController,
                    LocalPermissionManager provides permissionManager
                ) {
                    Scaffold { padding ->
                        NavHost(
                            modifier = Modifier.padding(padding),
                            navController = navController,
                            startDestination = Route.ComposePathListScreen,
                        ) {
                            composable<Route.ComposePathListScreen> { ComposePathListScreen() }

                            composable<Route.DocumentScanner> { DocumentScannerApp() }

                            composable<Route.SharedElement> { SceneAnimation() }

                            composable<Route.Encryption> { UiScreenEncryption() }
                        }
                    }
                }
            }
        }
    }
}


fun main(){

}





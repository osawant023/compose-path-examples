package com.jetpackcompose.compose_path_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jetpackcompose.compose_path_android.core.navigation.Route
import com.jetpackcompose.compose_path_android.presentation.document_scanner.DocumentScannerApp
import com.jetpackcompose.compose_path_android.presentation.notes_list.notes_list.notes_list.NotesApp
import com.jetpackcompose.compose_path_android.presentation.scene_animation.SceneAnimation
import com.jetpackcompose.compose_path_android.presentation.start.ComposePathListScreen
import com.jetpackcompose.compose_path_android.ui.theme.ComposePathTheme
import com.jetpackcompose.compose_path_android.util.Something

val LocalNavController = compositionLocalOf<NavHostController> {
    error("No NavController found!")
}
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Something()
            ComposePathTheme(dynamicColor = false) {
                val navController = rememberNavController()
                CompositionLocalProvider(
                    LocalNavController provides navController
                ) {
                    Scaffold { padding ->
                        NavHost(
                            modifier = Modifier.padding(padding),
                            navController = navController,
                            startDestination = Route.ComposePathListScreen,
                        ) {
                            composable<Route.ComposePathListScreen> {
                                ComposePathListScreen()
                            }

                            composable<Route.NotesList> {
                                NotesApp()
                            }

                            composable<Route.DocumentScanner> {
                                DocumentScannerApp()
                            }

                            composable<Route.SharedElement> {
                                SceneAnimation()
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposePathTheme(dynamicColor = false) {
        NotesApp()
    }
}





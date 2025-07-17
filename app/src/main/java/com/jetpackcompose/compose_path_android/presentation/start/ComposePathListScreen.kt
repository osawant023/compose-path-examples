package com.jetpackcompose.compose_path_android.presentation.start

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jetpackcompose.compose_path_android.LocalNavController
import com.jetpackcompose.compose_path_android.core.navigation.Route


typealias ScreenName = String
typealias Screen = Pair<ScreenName, Route>
val arrayScreenList = arrayListOf(
    Screen("Notes List", Route.NotesList),
    Screen("Document Scanner", Route.DocumentScanner),
    Screen("Scene Animation", Route.SharedElement),
    Screen("Encryption", Route.Encryption),
)

@Composable
fun ComposePathListScreen() {
    val navController = LocalNavController.current
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(arrayScreenList) { screen ->
            ListItem(
                modifier = Modifier.clickable {
                    navController.navigate(screen.second)
                },
                headlineContent = {
                    Text(screen.first)
                }
            )
        }
    }
}
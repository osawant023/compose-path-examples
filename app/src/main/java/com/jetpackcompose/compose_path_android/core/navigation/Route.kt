package com.jetpackcompose.compose_path_android.core.navigation
import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object ComposePathListScreen : Route

    @Serializable
    data object NotesList : Route

    @Serializable
    data object DocumentScanner : Route

    @Serializable
    data object SharedElement : Route


}
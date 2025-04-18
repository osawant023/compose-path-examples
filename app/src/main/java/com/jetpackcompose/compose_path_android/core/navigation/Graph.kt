package com.jetpackcompose.compose_path_android.core.navigation

import kotlinx.serialization.Serializable

sealed interface Graph {
    @Serializable
    data object Home : Graph

    @Serializable
    data object Brand : Graph

    @Serializable
    data object Category : Graph

    @Serializable
    data object Cart : Graph

    @Serializable
    data object Profile : Graph
}
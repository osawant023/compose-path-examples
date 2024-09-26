package com.jetpackcompose.compose_path_android.screens

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun main(){
    fetchUserData()
}

fun fetchUserData() {
    suspend fun getUserData(): String {
        return withContext(Dispatchers.IO) {
            // Simulate network request
            Thread.sleep(2000) // Simulate 2 second delay
            println("User data fetched 1")
            "User data fetched"
        }
    }

    suspend fun getUserStats(): String {
        return withContext(Dispatchers.IO) {
            // Simulate network request
            Thread.sleep(3000) // Simulate 3 second delay
            println("User data fetched 2")
            "User stats fetched"
        }
    }
    println("Start")
    CoroutineScope(Dispatchers.IO).launch {
        val userDataDeferred = async { getUserData() }
        val userStatsDeferred = async { getUserStats() }

        val userData = userDataDeferred.await()
        val userStats = userStatsDeferred.await()

        println("Complete")
    }
}
package com.example.accounts

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreen(navController : NavHostController = rememberNavController()){
    LaunchList2(
        onLaunchClick = { launchId ->
        }
    )
}
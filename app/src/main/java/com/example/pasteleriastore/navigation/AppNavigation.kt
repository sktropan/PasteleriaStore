package com.example.pasteleriastore.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pasteleriastore.ui.screen.HomeScreen
import com.example.pasteleriastore.ui.screen.LoginScreen
import com.example.pasteleriastore.ui.screen.RegistroScreen
import com.example.pasteleriastore.ui.screen.SplashScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash"){
        composable ("splash"){ SplashScreen(navController = navController) }
        composable ("login"){ LoginScreen(navController = navController) }
        composable ("home"){ HomeScreen(navController = navController) }
        composable ("registro"){ RegistroScreen(navController = navController) }
    }
}
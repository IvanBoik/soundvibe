package com.boiko.soundvibe.presentation.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.boiko.soundvibe.presentation.auth.AuthScreen
import com.boiko.soundvibe.presentation.auth.AuthViewModel

@Composable
fun NavGraph(startDestination: String) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Routes.AUTH_SCREEN) {
            val viewModel = hiltViewModel<AuthViewModel>()
            AuthScreen(
                navigate = navController::navigate,
                viewModel = viewModel
            )
        }
        composable(route = Routes.HOME_SCREEN) {
            Text(text = "Authorized!")
        }
    }
}
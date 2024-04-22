package com.boiko.soundvibe.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.boiko.soundvibe.presentation.onboarding.OnBoardingScreen
import com.boiko.soundvibe.presentation.onboarding.OnBoardingViewModel

@Composable
fun NavGraph(startDestination: String) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Routes.ON_BOARDING_SCREEN) {
            val viewModel = hiltViewModel<OnBoardingViewModel>()
            //OnBoardingScreen(event = viewModel::onEvent)
            OnBoardingScreen { route ->
                    navController.navigate(route)
            }
        }
    }
}
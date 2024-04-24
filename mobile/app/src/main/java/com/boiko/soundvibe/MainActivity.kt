package com.boiko.soundvibe

import android.graphics.Color.TRANSPARENT
import android.os.Bundle
import android.view.View
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.boiko.soundvibe.presentation.navigation.NavGraph
import com.boiko.soundvibe.ui.theme.SoundvibeTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Soundvibe)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                TRANSPARENT,
                TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.light(
                TRANSPARENT,
                TRANSPARENT
            )
        )
        super.onCreate(savedInstanceState)
        setContent {
            SoundvibeTheme {
                Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
                    val startDestination = viewModel.startDestination
                    NavGraph(startDestination = startDestination)
                }
            }
        }
    }
}
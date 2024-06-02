package com.boiko.soundvibe

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.boiko.soundvibe.domain.usecases.app_entry.AppEntryUseCases
import com.boiko.soundvibe.presentation.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    appEntryUseCases: AppEntryUseCases
): ViewModel() {
    var startDestination by mutableStateOf(Routes.AUTH_SCREEN)
        private set

    init {
        appEntryUseCases.readAppEntry().onEach { shouldStartFromHomeScreen ->
            startDestination = if (shouldStartFromHomeScreen) {
                Routes.HOME_SCREEN
            } else {
                Routes.AUTH_SCREEN
            }
        }.launchIn(viewModelScope)
    }
}
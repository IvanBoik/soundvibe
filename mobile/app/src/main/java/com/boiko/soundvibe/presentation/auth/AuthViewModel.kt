package com.boiko.soundvibe.presentation.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.boiko.soundvibe.auth.AuthRepository
import com.boiko.soundvibe.auth.AuthResult
import com.boiko.soundvibe.domain.usecases.app_entry.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {

    var state by mutableStateOf(AuthState())

    private val resultChannel = Channel<AuthResult<Unit>>()
    val authResults = resultChannel.receiveAsFlow()

    fun onEvent(event: AuthUiEvent) {
        when(event) {
            is AuthUiEvent.SignUpEmailChanged -> {
                state = state.copy(signUpEmail = event.value)
            }
            is AuthUiEvent.SignUpPasswordChanged -> {
                state = state.copy(signUpPassword = event.value)
            }
            is AuthUiEvent.SignUpNicknameChanged -> {
                state = state.copy(signUpNickname = event.value)
            }
            is AuthUiEvent.SignUpBirthdayChanged -> {
                state = state.copy(signUpBirthday = event.value)
            }
            is AuthUiEvent.SignUp -> {
                signUp()
            }

            is AuthUiEvent.SignInEmailChanged -> {
                state = state.copy(signInEmail = event.value)
            }
            is AuthUiEvent.SignInPasswordChanged -> {
                state = state.copy(signInPassword = event.value)
            }
            AuthUiEvent.SignIn -> {
                signIn()
            }
        }
    }

    private fun signUp() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result = repository.signUp(
                email = state.signUpEmail,
                password = state.signUpPassword,
                nickname = state.signUpNickname,
                birthday = state.signUpBirthday
            )
            resultChannel.send(result)
            state = state.copy(isLoading = false)
        }
    }

    private fun signIn() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result = repository.signIn(
                email = state.signInEmail,
                password = state.signInPassword
            )
            resultChannel.send(result)
            state = state.copy(isLoading = false)
        }
    }
}
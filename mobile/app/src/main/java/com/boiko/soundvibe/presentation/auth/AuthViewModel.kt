package com.boiko.soundvibe.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.boiko.soundvibe.auth.AuthRepository
import com.boiko.soundvibe.auth.AuthResult
import com.boiko.soundvibe.domain.usecases.app_entry.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {

    private val resultChannel = Channel<AuthResult<Unit>>()
    val authResults = resultChannel.receiveAsFlow()

    fun onEvent(event: AuthUiEvent) {
        when(event) {
            is AuthUiEvent.SignUp -> {
                signUp(
                    email = event.email,
                    password = event.password,
                    nickname = event.nickname,
                    birthday = event.birthday
                )
            }
            is AuthUiEvent.SignIn -> {
                signIn(
                    email = event.email,
                    password = event.password
                )
            }

            is AuthUiEvent.SaveAppEntry -> {
                saveAppEntry()
            }
        }
    }

    private fun signUp(email: String, password: String, nickname: String, birthday: LocalDate) {
        viewModelScope.launch {
            val result = repository.signUp(
                email = email,
                password = password,
                nickname = nickname,
                birthday = birthday
            )
            resultChannel.send(result)
        }
    }

    private fun signIn(email: String, password: String) {
        viewModelScope.launch {
            val result = repository.signIn(
                email = email,
                password = password
            )
            resultChannel.send(result)
        }
    }

    private fun saveAppEntry() {
        viewModelScope.launch {
            appEntryUseCases.saveAppEntry()
        }
    }
}
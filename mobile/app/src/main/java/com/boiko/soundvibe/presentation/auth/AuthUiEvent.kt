package com.boiko.soundvibe.presentation.auth

import java.time.LocalDate

sealed class AuthUiEvent {
    data class SignUp(val email: String, val password: String, val nickname: String, val birthday: LocalDate): AuthUiEvent()

    data class SignIn(val email: String, val password: String): AuthUiEvent()

    data object SaveAppEntry: AuthUiEvent()
}
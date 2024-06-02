package com.boiko.soundvibe.presentation.auth

import java.time.LocalDate

sealed class AuthUiEvent {
    data class SignUpEmailChanged(val value: String): AuthUiEvent()
    data class SignUpPasswordChanged(val value: String): AuthUiEvent()
    data class SignUpNicknameChanged(val value: String): AuthUiEvent()
    data class SignUpBirthdayChanged(val value: LocalDate): AuthUiEvent()
    data object SignUp: AuthUiEvent()

    data class SignInEmailChanged(val value: String): AuthUiEvent()
    data class SignInPasswordChanged(val value: String): AuthUiEvent()
    data object SignIn: AuthUiEvent()
}
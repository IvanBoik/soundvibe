package com.boiko.soundvibe.presentation.auth

import java.time.LocalDate

data class AuthState(
    val isLoading: Boolean = false,
    val signUpEmail: String = "",
    val signUpPassword: String = "",
    val signUpNickname: String = "",
    val signUpBirthday: LocalDate = LocalDate.now(),
    val signInEmail: String = "",
    val signInPassword: String = ""
)
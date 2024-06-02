package com.boiko.soundvibe.auth

import java.time.LocalDate

data class SignUpRequest(
    val email: String,
    val password: String,
    val nickname: String,
    val birthday: LocalDate
)

package com.boiko.soundvibe.auth

import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/signUp")
    suspend fun signUp(
        @Body request: SignUpRequest
    ): AuthResponse

    @POST("auth/signIn")
    suspend fun signIn(
        @Body request: SignInRequest
    ): AuthResponse
}
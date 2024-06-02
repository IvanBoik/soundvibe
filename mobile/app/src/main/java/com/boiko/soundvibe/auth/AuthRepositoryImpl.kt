package com.boiko.soundvibe.auth

import android.content.SharedPreferences
import retrofit2.HttpException
import java.time.LocalDate

class AuthRepositoryImpl(
    private val authApi: AuthApi,
    private val prefs: SharedPreferences
): AuthRepository {
    override suspend fun signUp(
        email: String,
        password: String,
        nickname: String,
        birthday: LocalDate
    ): AuthResult<Unit> {
        return try {
            val response = authApi.signUp(
                request = SignUpRequest(
                    email = email,
                    password = password,
                    nickname = nickname,
                    birthday = birthday
                )
            )
            prefs.edit()
                .putString("jwt", response.token)
                .putLong("id", response.id)
                .apply()

            AuthResult.Authorized()
        } catch (e: HttpException) {
            if (e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }

    override suspend fun signIn(email: String, password: String): AuthResult<Unit> {
        return try {
            val response = authApi.signIn(
                request = SignInRequest(
                    email = email,
                    password = password
                )
            )
            prefs.edit()
                .putString("jwt", response.token)
                .putLong("id", response.id)
                .apply()

            AuthResult.Authorized()
        } catch (e: HttpException) {
            if (e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }
}
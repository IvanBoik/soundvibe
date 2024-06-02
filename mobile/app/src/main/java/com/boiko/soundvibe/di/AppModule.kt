package com.boiko.soundvibe.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.boiko.soundvibe.auth.AuthApi
import com.boiko.soundvibe.auth.AuthRepository
import com.boiko.soundvibe.auth.AuthRepositoryImpl
import com.boiko.soundvibe.data.manager.LocalUserManagerImpl
import com.boiko.soundvibe.domain.manager.LocalUserManager
import com.boiko.soundvibe.domain.usecases.app_entry.AppEntryUseCases
import com.boiko.soundvibe.domain.usecases.app_entry.ReadAppEntry
import com.boiko.soundvibe.domain.usecases.app_entry.SaveAppEntry
import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.time.LocalDate
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ) : LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )
    
    @Provides
    @Singleton
    fun provideAuthApi(): AuthApi {
        val moshi = Moshi.Builder()
            .add(LocaleDateAdapter)
            .build()
        return Retrofit.Builder()
            .baseUrl("http:10.0.2.2:8080/api/v1/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideSharedPref(app: Application): SharedPreferences {
        return app.getSharedPreferences("prefs", MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(api: AuthApi, prefs: SharedPreferences): AuthRepository {
        return AuthRepositoryImpl(api, prefs)
    }
}

object LocaleDateAdapter {
    @ToJson
    fun localDateToJson(localDate: LocalDate): String {
        return localDate.toString()
    }

    @FromJson
    fun jsonToLocalDate(json: String): LocalDate {
        return LocalDate.parse(json)
    }
}
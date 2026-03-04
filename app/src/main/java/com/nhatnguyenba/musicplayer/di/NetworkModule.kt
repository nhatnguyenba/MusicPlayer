package com.nhatnguyenba.musicplayer.di

import android.util.Log
import com.nhatnguyenba.musicplayer.data.remote.api.DeezerMusicService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    val DEEZER_BASE_URL = "https://api.deezer.com/"

    @Provides
    @Singleton
    fun provideDeezerMusicApi(
    ): DeezerMusicService {
        val baseUrl = DEEZER_BASE_URL

        Log.d("NetworkModule", "deezerBaseUrl: $baseUrl")

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .addInterceptor { chain ->
                        val request = chain.request().newBuilder()
                            .build()
                        chain.proceed(request)
                    }
                    .build()
            )
            .build()
            .create(DeezerMusicService::class.java)
    }
}
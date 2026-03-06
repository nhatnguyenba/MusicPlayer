package com.nhatnguyenba.musicplayer.di

import android.content.Context
import com.nhatnguyenba.musicplayer.data.manager.LocalSongManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Provides
    @Singleton
    fun provideLocalSongManager(
        @ApplicationContext context: Context
    ): LocalSongManager {
        return LocalSongManager(context)
    }
}
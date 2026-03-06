package com.nhatnguyenba.musicplayer.di

import android.content.ComponentName
import android.content.Context
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.ListenableFuture
import com.nhatnguyenba.musicplayer.service.PlaybackService
import com.nhatnguyenba.musicplayer.data.player.PlayerManager
import com.nhatnguyenba.musicplayer.data.repositories.PlayerRepositoryImpl
import com.nhatnguyenba.musicplayer.domain.repositories.PlayerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PlayerModule {

    @Provides
    @Singleton
    fun provideMediaControllerFuture(
        @ApplicationContext context: Context
    ): ListenableFuture<MediaController> {

        val sessionToken = SessionToken(
            context,
            ComponentName(context, PlaybackService::class.java)
        )

        return MediaController.Builder(context, sessionToken)
            .buildAsync()
    }

    @Provides
    @Singleton
    fun providePlayerRepository(
        playerManager: PlayerManager
    ): PlayerRepository {

        return PlayerRepositoryImpl(playerManager)
    }
}
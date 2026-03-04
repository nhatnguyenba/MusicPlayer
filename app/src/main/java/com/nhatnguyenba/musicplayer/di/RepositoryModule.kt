package com.nhatnguyenba.musicplayer.di

import com.nhatnguyenba.musicplayer.data.remote.api.DeezerMusicService
import com.nhatnguyenba.musicplayer.data.repositories.GenreRepositoryImpl
import com.nhatnguyenba.musicplayer.data.repositories.PlaylistRepositoryImpl
import com.nhatnguyenba.musicplayer.domain.repositories.GenreRepository
import com.nhatnguyenba.musicplayer.domain.repositories.PlaylistRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providePlaylistRepository(
        deezerMusicService: DeezerMusicService
    ): PlaylistRepository = PlaylistRepositoryImpl(deezerMusicService)

    @Provides
    @Singleton
    fun provideGenreRepository(
        deezerMusicService: DeezerMusicService
    ): GenreRepository = GenreRepositoryImpl(deezerMusicService)
}
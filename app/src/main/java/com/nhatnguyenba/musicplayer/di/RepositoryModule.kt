package com.nhatnguyenba.musicplayer.di

import com.nhatnguyenba.musicplayer.data.remote.api.DeezerMusicService
import com.nhatnguyenba.musicplayer.data.repositories.AlbumRepositoryImpl
import com.nhatnguyenba.musicplayer.data.repositories.ArtistRepositoryImpl
import com.nhatnguyenba.musicplayer.data.repositories.GenreRepositoryImpl
import com.nhatnguyenba.musicplayer.data.repositories.PlaylistRepositoryImpl
import com.nhatnguyenba.musicplayer.data.repositories.SongRepositoryImpl
import com.nhatnguyenba.musicplayer.domain.repositories.AlbumRepository
import com.nhatnguyenba.musicplayer.domain.repositories.ArtistRepository
import com.nhatnguyenba.musicplayer.domain.repositories.GenreRepository
import com.nhatnguyenba.musicplayer.domain.repositories.PlaylistRepository
import com.nhatnguyenba.musicplayer.domain.repositories.SongRepository
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

    @Provides
    @Singleton
    fun provideSongRepository(
        deezerMusicService: DeezerMusicService
    ): SongRepository = SongRepositoryImpl(deezerMusicService)

    @Provides
    @Singleton
    fun provideArtistRepository(
        deezerMusicService: DeezerMusicService
    ): ArtistRepository = ArtistRepositoryImpl(deezerMusicService)

    @Provides
    @Singleton
    fun provideAlbumRepository(
        deezerMusicService: DeezerMusicService
    ): AlbumRepository = AlbumRepositoryImpl(deezerMusicService)
}
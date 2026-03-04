package com.nhatnguyenba.musicplayer.data.repositories

import com.nhatnguyenba.musicplayer.data.mapper.toDomain
import com.nhatnguyenba.musicplayer.data.mapper.toFlow
import com.nhatnguyenba.musicplayer.data.remote.api.DeezerMusicService
import com.nhatnguyenba.musicplayer.domain.models.Genre
import com.nhatnguyenba.musicplayer.domain.repositories.GenreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
    private val deezerMusicService: DeezerMusicService
) : GenreRepository {
    override fun getAllGenres(): Flow<List<Genre>> {
        return deezerMusicService.getGenres().toFlow().map {
            it.data.map { genreDto ->
                genreDto.toDomain()
            }
        }
    }
}
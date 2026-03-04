package com.nhatnguyenba.musicplayer.domain.repositories

import com.nhatnguyenba.musicplayer.domain.models.Genre
import kotlinx.coroutines.flow.Flow

interface GenreRepository {
    fun getAllGenres(): Flow<List<Genre>>
}
package com.nhatnguyenba.musicplayer.domain.usecases

import com.nhatnguyenba.musicplayer.domain.models.Genre
import com.nhatnguyenba.musicplayer.domain.repositories.GenreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllGenresUseCase @Inject constructor(
    private val repository: GenreRepository
) {
    operator fun invoke(): Flow<List<Genre>> {
        return repository.getAllGenres()
    }
}
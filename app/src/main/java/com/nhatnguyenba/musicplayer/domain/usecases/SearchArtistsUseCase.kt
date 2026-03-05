package com.nhatnguyenba.musicplayer.domain.usecases

import com.nhatnguyenba.musicplayer.domain.models.Artist
import com.nhatnguyenba.musicplayer.domain.repositories.ArtistRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchArtistsUseCase @Inject constructor(
    private val repository: ArtistRepository
) {
    operator fun invoke(keyword: String): Flow<List<Artist>> {
        return repository.searchArtists(keyword)
    }
}
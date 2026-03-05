package com.nhatnguyenba.musicplayer.domain.usecases

import com.nhatnguyenba.musicplayer.domain.models.Album
import com.nhatnguyenba.musicplayer.domain.repositories.AlbumRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchAlbumsUseCase @Inject constructor(
    private val repository: AlbumRepository
) {
    operator fun invoke(keyword: String): Flow<List<Album>> {
        return repository.searchAlbums(keyword)
    }
}
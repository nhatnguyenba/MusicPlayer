package com.nhatnguyenba.musicplayer.domain.usecases

import com.nhatnguyenba.musicplayer.domain.models.Song
import com.nhatnguyenba.musicplayer.domain.repositories.SongRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchSongsUseCase @Inject constructor(
    private val repository: SongRepository
) {
    operator fun invoke(keyword: String): Flow<List<Song>> {
        return repository.searchSongs(keyword)
    }
}
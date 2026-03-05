package com.nhatnguyenba.musicplayer.domain.usecases

import com.nhatnguyenba.musicplayer.domain.models.Playlist
import com.nhatnguyenba.musicplayer.domain.repositories.PlaylistRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchPlaylistsUseCase @Inject constructor(
    private val repository: PlaylistRepository
) {
    operator fun invoke(keyword: String): Flow<List<Playlist>> {
        return repository.searchPlaylists(keyword)
    }
}
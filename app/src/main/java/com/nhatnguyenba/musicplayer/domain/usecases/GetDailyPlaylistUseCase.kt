package com.nhatnguyenba.musicplayer.domain.usecases

import com.nhatnguyenba.musicplayer.domain.models.Playlist
import com.nhatnguyenba.musicplayer.domain.repositories.PlaylistRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDailyPlaylistUseCase @Inject constructor(
    private val repository: PlaylistRepository
) {
    operator fun invoke(): Flow<List<Playlist>> {
        return repository.getDailyPlaylists()
    }
}
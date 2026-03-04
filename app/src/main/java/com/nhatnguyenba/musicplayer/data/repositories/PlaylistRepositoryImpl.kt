package com.nhatnguyenba.musicplayer.data.repositories

import com.nhatnguyenba.musicplayer.data.mapper.toDomain
import com.nhatnguyenba.musicplayer.data.mapper.toFlow
import com.nhatnguyenba.musicplayer.data.remote.api.DeezerMusicService
import com.nhatnguyenba.musicplayer.domain.models.Playlist
import com.nhatnguyenba.musicplayer.domain.repositories.PlaylistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PlaylistRepositoryImpl @Inject constructor(
    private val deezerMusicService: DeezerMusicService
) : PlaylistRepository {
    override fun getDailyPlaylists(): Flow<List<Playlist>> {
        return deezerMusicService.getDailyPlaylists().toFlow().map {
            it.data.map { playlistDto ->
                playlistDto.toDomain()
            }
        }
    }
}
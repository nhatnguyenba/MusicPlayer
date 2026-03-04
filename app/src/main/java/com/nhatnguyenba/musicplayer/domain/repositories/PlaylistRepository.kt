package com.nhatnguyenba.musicplayer.domain.repositories

import com.nhatnguyenba.musicplayer.domain.models.Playlist
import kotlinx.coroutines.flow.Flow

interface PlaylistRepository {
    fun getDailyPlaylists(): Flow<List<Playlist>>
}
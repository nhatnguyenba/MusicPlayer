package com.nhatnguyenba.musicplayer.domain.repositories

import com.nhatnguyenba.musicplayer.domain.models.Song
import kotlinx.coroutines.flow.Flow

interface SongRepository {
    fun searchSongs(keyword: String): Flow<List<Song>>
}
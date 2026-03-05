package com.nhatnguyenba.musicplayer.domain.repositories

import com.nhatnguyenba.musicplayer.domain.models.Album
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {
    fun searchAlbums(keyword: String): Flow<List<Album>>
}
package com.nhatnguyenba.musicplayer.domain.repositories

import com.nhatnguyenba.musicplayer.domain.models.Artist
import kotlinx.coroutines.flow.Flow

interface ArtistRepository {
    fun searchArtists(keyword: String): Flow<List<Artist>>
}
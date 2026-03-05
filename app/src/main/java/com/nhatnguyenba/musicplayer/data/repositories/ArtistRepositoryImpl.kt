package com.nhatnguyenba.musicplayer.data.repositories

import com.nhatnguyenba.musicplayer.data.mapper.toDomain
import com.nhatnguyenba.musicplayer.data.mapper.toFlow
import com.nhatnguyenba.musicplayer.data.remote.api.DeezerMusicService
import com.nhatnguyenba.musicplayer.domain.models.Artist
import com.nhatnguyenba.musicplayer.domain.repositories.ArtistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ArtistRepositoryImpl @Inject constructor(
    private val deezerMusicService: DeezerMusicService
) : ArtistRepository {
    override fun searchArtists(keyword: String): Flow<List<Artist>> {
        return deezerMusicService.searchArtists(keyword).toFlow().map {
            it.data.map { artistDto ->
                artistDto.toDomain()
            }
        }
    }
}
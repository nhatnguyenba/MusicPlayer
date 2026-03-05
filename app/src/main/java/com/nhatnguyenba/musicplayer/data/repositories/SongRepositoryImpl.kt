package com.nhatnguyenba.musicplayer.data.repositories

import com.nhatnguyenba.musicplayer.data.mapper.toDomain
import com.nhatnguyenba.musicplayer.data.mapper.toFlow
import com.nhatnguyenba.musicplayer.data.remote.api.DeezerMusicService
import com.nhatnguyenba.musicplayer.domain.models.Song
import com.nhatnguyenba.musicplayer.domain.repositories.SongRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SongRepositoryImpl @Inject constructor(
    private val deezerMusicService: DeezerMusicService
) : SongRepository {
    override fun searchSongs(keyword: String): Flow<List<Song>> {
        return deezerMusicService.searchTracks(keyword).toFlow().map {
            it.data.map { trackDto ->
                trackDto.toDomain()
            }
        }
    }
}
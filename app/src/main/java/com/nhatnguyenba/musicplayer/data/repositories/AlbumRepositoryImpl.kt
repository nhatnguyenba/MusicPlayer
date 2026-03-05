package com.nhatnguyenba.musicplayer.data.repositories

import com.nhatnguyenba.musicplayer.data.mapper.toDomain
import com.nhatnguyenba.musicplayer.data.mapper.toFlow
import com.nhatnguyenba.musicplayer.data.remote.api.DeezerMusicService
import com.nhatnguyenba.musicplayer.domain.models.Album
import com.nhatnguyenba.musicplayer.domain.repositories.AlbumRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(
    private val deezerMusicService: DeezerMusicService
) : AlbumRepository {
    override fun searchAlbums(keyword: String): Flow<List<Album>> {
        return deezerMusicService.searchAlbums(keyword).toFlow().map {
            it.data.map { albumDto ->
                albumDto.toDomain()
            }
        }
    }
}
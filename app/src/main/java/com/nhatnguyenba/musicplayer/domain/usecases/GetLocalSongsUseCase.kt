package com.nhatnguyenba.musicplayer.domain.usecases

import com.nhatnguyenba.musicplayer.domain.models.Song
import com.nhatnguyenba.musicplayer.domain.repositories.SongRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocalSongsUseCase @Inject constructor(
    private val repository: SongRepository
) {
    operator fun invoke(): Flow<List<Song>> {
        return repository.getLocalSongs()
    }
}
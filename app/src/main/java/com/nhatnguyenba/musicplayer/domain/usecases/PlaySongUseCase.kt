package com.nhatnguyenba.musicplayer.domain.usecases

import com.nhatnguyenba.musicplayer.domain.models.Song
import com.nhatnguyenba.musicplayer.domain.repositories.PlayerRepository
import javax.inject.Inject

class PlaySongUseCase @Inject constructor(
    private val repository: PlayerRepository
) {

    operator fun invoke(song: Song) {
        repository.play(song)
    }
}
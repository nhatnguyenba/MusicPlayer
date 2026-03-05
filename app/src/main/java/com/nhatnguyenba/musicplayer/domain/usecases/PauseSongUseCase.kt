package com.nhatnguyenba.musicplayer.domain.usecases

import com.nhatnguyenba.musicplayer.domain.repositories.PlayerRepository
import javax.inject.Inject

class PauseSongUseCase @Inject constructor(
    private val repository: PlayerRepository
) {

    operator fun invoke() {
        repository.pause()
    }
}
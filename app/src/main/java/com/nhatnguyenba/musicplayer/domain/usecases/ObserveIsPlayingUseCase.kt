package com.nhatnguyenba.musicplayer.domain.usecases

import com.nhatnguyenba.musicplayer.domain.repositories.PlayerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveIsPlayingUseCase @Inject constructor(
    private val repository: PlayerRepository
) {

    operator fun invoke(): Flow<Boolean> {
        return repository.observeIsPlaying()
    }
}
package com.nhatnguyenba.musicplayer.domain.usecases

import com.nhatnguyenba.musicplayer.domain.repositories.PlayerRepository
import javax.inject.Inject

class ObserveDurationUseCase @Inject constructor(
    private val repository: PlayerRepository
) {
    operator fun invoke() = repository.observeDuration()
}
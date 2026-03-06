package com.nhatnguyenba.musicplayer.data.repositories

import com.nhatnguyenba.musicplayer.data.manager.PlayerManager
import com.nhatnguyenba.musicplayer.domain.repositories.PlayerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlayerRepositoryImpl @Inject constructor(
    private val playerManager: PlayerManager
) : PlayerRepository {

    override fun play(url: String) {
        playerManager.play(url)
    }

    override fun pause() {
        playerManager.pause()
    }

    override fun resume() {
        playerManager.play()
    }

    override fun seekTo(position: Long) {
        playerManager.seekTo(position)
    }

    override fun observeDuration(): Flow<Long> {
        return playerManager.observeDuration()
    }

    override fun observeCurrentPosition(): Flow<Long> {
        return playerManager.observeCurrentPosition()
    }

    override fun observeIsPlaying(): Flow<Boolean> {
        return playerManager.observeIsPlaying()
    }
}
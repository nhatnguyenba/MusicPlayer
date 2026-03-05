package com.nhatnguyenba.musicplayer.domain.repositories

import kotlinx.coroutines.flow.Flow

interface PlayerRepository {

    fun play(url: String)

    fun pause()

    fun resume()

    fun seekTo(position: Long)

    fun observeDuration(): Flow<Long>

    fun observeCurrentPosition(): Flow<Long>

    fun observeIsPlaying(): Flow<Boolean>
}
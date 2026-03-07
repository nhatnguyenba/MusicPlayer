package com.nhatnguyenba.musicplayer.domain.repositories

import com.nhatnguyenba.musicplayer.domain.models.Song
import kotlinx.coroutines.flow.Flow

interface PlayerRepository {

    fun play(song: Song)

    fun pause()

    fun resume()

    fun seekTo(position: Long)

    fun observeDuration(): Flow<Long>

    fun observeCurrentPosition(): Flow<Long>

    fun observeIsPlaying(): Flow<Boolean>
}
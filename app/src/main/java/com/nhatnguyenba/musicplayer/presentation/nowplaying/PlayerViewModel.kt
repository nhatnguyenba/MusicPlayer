package com.nhatnguyenba.musicplayer.presentation.nowplaying

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nhatnguyenba.musicplayer.domain.models.Song
import com.nhatnguyenba.musicplayer.domain.usecases.ObserveCurrentPositionUseCase
import com.nhatnguyenba.musicplayer.domain.usecases.ObserveDurationUseCase
import com.nhatnguyenba.musicplayer.domain.usecases.ObserveIsPlayingUseCase
import com.nhatnguyenba.musicplayer.domain.usecases.PauseSongUseCase
import com.nhatnguyenba.musicplayer.domain.usecases.PlaySongUseCase
import com.nhatnguyenba.musicplayer.domain.usecases.ResumeSongUseCase
import com.nhatnguyenba.musicplayer.domain.usecases.SeekToUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val playSongUseCase: PlaySongUseCase,
    private val pauseSongUseCase: PauseSongUseCase,
    private val resumeSongUseCase: ResumeSongUseCase,
    private val observeIsPlayingUseCase: ObserveIsPlayingUseCase,
    private val observeDuration: ObserveDurationUseCase,
    private val observeCurrentPosition: ObserveCurrentPositionUseCase,
    private val seekToUseCase: SeekToUseCase
) : ViewModel() {

    private val _currentSong = MutableStateFlow<Song?>(null)
    val currentSong = _currentSong.asStateFlow()

    fun playSong(song: Song) {
        _currentSong.value = song
        playSongUseCase(song)
    }

    fun pause() {
        pauseSongUseCase()
    }

    val isPlaying = observeIsPlayingUseCase()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            false
        )

    val duration = observeDuration()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), 0L)

    val currentPosition = observeCurrentPosition()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), 0L)

    val progress: StateFlow<Float> =
        combine(currentPosition, duration) { position, duration ->

            if (duration <= 0) 0f
            else position.toFloat() / duration

        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            0f
        )

    fun seekTo(progress: Float) {

        val position = (duration.value * progress).toLong()
        if (position < 0 || position > duration.value) return
        seekToUseCase(position)
    }

    fun onPlayPauseClick() {

        if (isPlaying.value) {
            pauseSongUseCase()
        } else {
            resumeSongUseCase()
        }
    }
}
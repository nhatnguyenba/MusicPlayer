package com.nhatnguyenba.musicplayer.presentation.nowplaying

import androidx.lifecycle.ViewModel
import com.nhatnguyenba.musicplayer.domain.models.Song
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor() : ViewModel() {

    private val _currentSong = MutableStateFlow<Song?>(null)
    val currentSong = _currentSong.asStateFlow()

    fun playSong(song: Song) {
        _currentSong.value = song
    }
}
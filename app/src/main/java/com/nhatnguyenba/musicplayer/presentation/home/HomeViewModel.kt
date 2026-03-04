package com.nhatnguyenba.musicplayer.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nhatnguyenba.musicplayer.domain.models.Playlist
import com.nhatnguyenba.musicplayer.domain.usecases.GetDailyPlaylistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDailyPlaylistUseCase: GetDailyPlaylistUseCase
) : ViewModel() {
    val uiState: StateFlow<HomeUiState> =
        getDailyPlaylistUseCase()
            .map { songs ->
                HomeUiState.Success(songs)
            }
            .catch { e ->
                HomeUiState.Error(e.message ?: "Unknown error")
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = HomeUiState.Loading
            )
}

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(val playlists: List<Playlist>) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}
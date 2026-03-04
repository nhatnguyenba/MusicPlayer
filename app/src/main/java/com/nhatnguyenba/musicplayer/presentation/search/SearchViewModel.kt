package com.nhatnguyenba.musicplayer.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nhatnguyenba.musicplayer.domain.models.Genre
import com.nhatnguyenba.musicplayer.domain.usecases.GetAllGenresUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getAllGenresUseCase: GetAllGenresUseCase
) : ViewModel() {
    val uiState: StateFlow<SearchUiState> =
        getAllGenresUseCase()
            .map { genres ->
                SearchUiState.Success(genres)
            }
            .catch { e ->
                SearchUiState.Error(e.message ?: "Unknown error")
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = SearchUiState.Loading
            )
}

sealed class SearchUiState {
    data object Loading : SearchUiState()
    data class Success(val genres: List<Genre>) : SearchUiState()
    data class Error(val message: String) : SearchUiState()
}
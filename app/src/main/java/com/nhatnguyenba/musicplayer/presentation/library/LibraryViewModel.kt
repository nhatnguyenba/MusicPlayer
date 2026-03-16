package com.nhatnguyenba.musicplayer.presentation.library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nhatnguyenba.musicplayer.domain.usecases.GetLocalSongsUseCase
import com.nhatnguyenba.musicplayer.presentation.common.UiState
import com.nhatnguyenba.musicplayer.presentation.search.SearchResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val getLocalSongsUseCase: GetLocalSongsUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<SearchResult>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<SearchResult>> = _uiState

    private val _currentFilter: MutableStateFlow<LibraryFilter> =
        MutableStateFlow(LibraryFilter.DOWNLOADED)
    val currentFilter: StateFlow<LibraryFilter> = _currentFilter

    private var job: Job? = null

    init {
        loadLibraryData()
    }

    fun onFilterChange(filter: LibraryFilter) {
        _currentFilter.value = filter

        loadLibraryData()
    }

    private fun loadLibraryData() {
        when (currentFilter.value) {
            LibraryFilter.DOWNLOADED -> {
                job?.cancel()
                job = viewModelScope.launch {
                    getLocalSongsUseCase().collect {
                        _uiState.value = UiState.Success(SearchResult.Songs(it))
                    }
                }
            }

            LibraryFilter.SONGS -> {

            }

            LibraryFilter.ALBUMS -> {

            }

            LibraryFilter.PLAYLISTS -> {

            }

            LibraryFilter.ARTISTS -> {

            }

            LibraryFilter.PODCASTS -> {

            }
        }
    }
}

enum class LibraryFilter(val title: String) {
    DOWNLOADED("Downloaded"),
    SONGS("Songs"),
    ALBUMS("Albums"),
    PLAYLISTS("Playlists"),
    ARTISTS("Artists"),
    PODCASTS("Podcasts")
}
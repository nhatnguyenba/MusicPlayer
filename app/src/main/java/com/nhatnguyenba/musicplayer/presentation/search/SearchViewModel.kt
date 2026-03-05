package com.nhatnguyenba.musicplayer.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nhatnguyenba.musicplayer.domain.models.Album
import com.nhatnguyenba.musicplayer.domain.models.Artist
import com.nhatnguyenba.musicplayer.domain.models.Genre
import com.nhatnguyenba.musicplayer.domain.models.Playlist
import com.nhatnguyenba.musicplayer.domain.models.Song
import com.nhatnguyenba.musicplayer.domain.usecases.GetAllGenresUseCase
import com.nhatnguyenba.musicplayer.domain.usecases.SearchAlbumsUseCase
import com.nhatnguyenba.musicplayer.domain.usecases.SearchArtistsUseCase
import com.nhatnguyenba.musicplayer.domain.usecases.SearchPlaylistsUseCase
import com.nhatnguyenba.musicplayer.domain.usecases.SearchSongsUseCase
import com.nhatnguyenba.musicplayer.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getAllGenresUseCase: GetAllGenresUseCase,
    private val searchSongsUseCase: SearchSongsUseCase,
    private val searchArtistsUseCase: SearchArtistsUseCase,
    private val searchAlbumsUseCase: SearchAlbumsUseCase,
    private val searchPlaylistsUseCase: SearchPlaylistsUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<SearchResult>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<SearchResult>> = _uiState

    private var currentQuery = ""
    private var currentFilter = SearchFilter.SONGS

    private var scope = CoroutineScope(Dispatchers.IO)

    init {
        loadGenres()
    }

    fun onQueryChange(query: String) {
        currentQuery = query

        if (query.isBlank()) {
            loadGenres()
        } else {
            search(query)
        }
    }

    fun onFilterChange(filter: SearchFilter) {
        currentFilter = filter

        if (currentQuery.isNotBlank()) {
            search(currentQuery)
        }
    }

    private fun loadGenres() {
        scope.cancel()
        scope = CoroutineScope(Dispatchers.IO)
        viewModelScope.launch {
            scope.launch {
                getAllGenresUseCase()
                    .onStart { _uiState.value = UiState.Loading }
                    .catch { e ->
                        _uiState.value = UiState.Error(e.message ?: "Error")
                    }
                    .collect { genres ->
                        _uiState.value =
                            UiState.Success(SearchResult.Genres(genres))
                    }
            }
        }
    }

    private fun search(query: String) {
        scope.cancel()
        scope = CoroutineScope(Dispatchers.IO)
        viewModelScope.launch {
            scope.launch {
                _uiState.value = UiState.Loading

                when (currentFilter) {

                    SearchFilter.SONGS -> {
                        searchSongsUseCase(query).collect { songs ->
                            _uiState.value =
                                UiState.Success(SearchResult.Songs(songs))
                        }
                    }

                    SearchFilter.ARTISTS -> {
                        searchArtistsUseCase(query).collect { artists ->
                            _uiState.value =
                                UiState.Success(SearchResult.Artists(artists))
                        }
                    }

                    SearchFilter.ALBUMS -> {
                        searchAlbumsUseCase(query).collect { albums ->
                            _uiState.value =
                                UiState.Success(SearchResult.Albums(albums))
                        }
                    }

                    SearchFilter.PLAYLISTS -> {
                        searchPlaylistsUseCase(query).collect { playlists ->
                            _uiState.value =
                                UiState.Success(SearchResult.Playlists(playlists))
                        }
                    }

                    SearchFilter.PODCASTS -> {

                    }
                }
            }
        }
    }
}

enum class SearchFilter(val title: String) {
    SONGS("Songs"),
    ARTISTS("Artists"),
    ALBUMS("Albums"),
    PLAYLISTS("Playlists"),
    PODCASTS("Podcasts")
}

sealed class SearchResult {

    data class Genres(val genres: List<Genre>) : SearchResult()

    data class Songs(val songs: List<Song>) : SearchResult()

    data class Artists(val artists: List<Artist>) : SearchResult()

    data class Albums(val albums: List<Album>) : SearchResult()

    data class Playlists(val playlists: List<Playlist>) : SearchResult()
}
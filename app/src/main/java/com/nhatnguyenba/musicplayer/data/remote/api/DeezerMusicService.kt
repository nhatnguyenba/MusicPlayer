package com.nhatnguyenba.musicplayer.data.remote.api

import com.nhatnguyenba.musicplayer.data.remote.dto.AlbumsResponse
import com.nhatnguyenba.musicplayer.data.remote.dto.ArtistsResponse
import com.nhatnguyenba.musicplayer.data.remote.dto.GenresResponse
import com.nhatnguyenba.musicplayer.data.remote.dto.PlaylistsResponse
import com.nhatnguyenba.musicplayer.data.remote.dto.TracksResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DeezerMusicService {
    @GET("chart/0/playlists")
    fun getDailyPlaylists(): Call<PlaylistsResponse>

    @GET("genre")
    fun getGenres(): Call<GenresResponse>

    @GET("search/track")
    fun searchTracks(@Query("q") query: String): Call<TracksResponse>

    @GET("search/artist")
    fun searchArtists(@Query("q") query: String): Call<ArtistsResponse>

    @GET("search/album")
    fun searchAlbums(@Query("q") query: String): Call<AlbumsResponse>

    @GET("search/playlist")
    fun searchPlaylists(@Query("q") query: String): Call<PlaylistsResponse>
}

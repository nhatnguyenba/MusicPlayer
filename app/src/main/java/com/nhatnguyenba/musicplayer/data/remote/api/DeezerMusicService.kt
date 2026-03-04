package com.nhatnguyenba.musicplayer.data.remote.api

import com.nhatnguyenba.musicplayer.data.remote.dto.DailyPlaylistsResponse
import com.nhatnguyenba.musicplayer.data.remote.dto.GenresResponse
import retrofit2.Call
import retrofit2.http.GET

interface DeezerMusicService {
    @GET("chart/0/playlists")
    fun getDailyPlaylists(): Call<DailyPlaylistsResponse>

    @GET("genre")
    fun getGenres(): Call<GenresResponse>
}

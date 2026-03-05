package com.nhatnguyenba.musicplayer.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AlbumsResponse(
    val data: List<AlbumDto>,
    val total: Long,
    val next: String,
)

data class AlbumDto(
    val id: String,
    val title: String,
    val link: String,
    val cover: String,
    @SerializedName("cover_small")
    val coverSmall: String,
    @SerializedName("cover_medium")
    val coverMedium: String,
    @SerializedName("cover_big")
    val coverBig: String,
    @SerializedName("cover_xl")
    val coverXl: String,
    @SerializedName("md5_image")
    val md5Image: String,
    @SerializedName("genre_id")
    val genreId: Long,
    @SerializedName("nb_tracks")
    val nbTracks: Long,
    @SerializedName("record_type")
    val recordType: String,
    val tracklist: String,
    @SerializedName("explicit_lyrics")
    val explicitLyrics: Boolean,
    val artist: ArtistDto,
    val type: String,
)
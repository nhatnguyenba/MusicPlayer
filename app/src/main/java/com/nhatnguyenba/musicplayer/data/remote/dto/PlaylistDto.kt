package com.nhatnguyenba.musicplayer.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PlaylistsResponse(
    val data: List<PlaylistDto>,
    val total: Long,
)

data class PlaylistDto(
    val id: String,
    val title: String,
    val public: Boolean,
    @SerializedName("nb_tracks")
    val nbTracks: Long,
    val link: String,
    val picture: String,
    @SerializedName("picture_small")
    val pictureSmall: String,
    @SerializedName("picture_medium")
    val pictureMedium: String,
    @SerializedName("picture_big")
    val pictureBig: String,
    @SerializedName("picture_xl")
    val pictureXl: String,
    val checksum: String,
    val tracklist: String,
    @SerializedName("creation_date")
    val creationDate: String,
    @SerializedName("add_date")
    val addDate: String,
    @SerializedName("mod_date")
    val modDate: String,
    @SerializedName("md5_image")
    val md5Image: String,
    @SerializedName("picture_type")
    val pictureType: String,
    val user: User,
    val type: String,
)

data class User(
    val id: String,
    val name: String,
    val tracklist: String,
    val type: String,
)
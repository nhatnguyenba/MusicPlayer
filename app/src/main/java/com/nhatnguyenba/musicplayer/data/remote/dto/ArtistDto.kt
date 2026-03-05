package com.nhatnguyenba.musicplayer.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ArtistsResponse(
    val data: List<ArtistDto>,
    val total: Long,
    val next: String,
)

data class ArtistDto(
    val id: String,
    val name: String,
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
    @SerializedName("nb_album")
    val nbAlbum: Long,
    @SerializedName("nb_fan")
    val nbFan: Long,
    val radio: Boolean,
    val tracklist: String,
    val type: String,
)
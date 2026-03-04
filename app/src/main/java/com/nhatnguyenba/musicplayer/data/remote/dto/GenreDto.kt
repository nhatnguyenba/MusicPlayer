package com.nhatnguyenba.musicplayer.data.remote.dto

import com.google.gson.annotations.SerializedName

data class GenresResponse(
    val data: List<GenreDto>,
)

data class GenreDto(
    val id: String,
    val name: String,
    val picture: String,
    @SerializedName("picture_small")
    val pictureSmall: String,
    @SerializedName("picture_medium")
    val pictureMedium: String,
    @SerializedName("picture_big")
    val pictureBig: String,
    @SerializedName("picture_xl")
    val pictureXl: String,
    val type: String,
)
package com.nhatnguyenba.musicplayer.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TracksResponse(
    val data: List<TrackDto>,
    val total: Long,
    val next: String,
)

data class TrackDto(
    val id: String,
    val readable: Boolean,
    val title: String,
    @SerializedName("title_short")
    val titleShort: String,
    @SerializedName("title_version")
    val titleVersion: String,
    val isrc: String,
    val link: String,
    val duration: Long,
    val rank: Long,
    @SerializedName("explicit_lyrics")
    val explicitLyrics: Boolean,
    @SerializedName("explicit_content_lyrics")
    val explicitContentLyrics: Long,
    @SerializedName("explicit_content_cover")
    val explicitContentCover: Long,
    val preview: String,
    @SerializedName("md5_image")
    val md5Image: String,
    val artist: ArtistDto,
    val album: AlbumDto,
    val type: String,
)
package com.nhatnguyenba.musicplayer.domain.models

class Playlist(
    val id: String,
    val title: String,
    val author: String,
    val description: String,
    val imageUrl: String,
    val numberOfTracks: Int,
)
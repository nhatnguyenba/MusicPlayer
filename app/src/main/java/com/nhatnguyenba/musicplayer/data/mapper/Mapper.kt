package com.nhatnguyenba.musicplayer.data.mapper

import com.nhatnguyenba.musicplayer.data.remote.dto.AlbumDto
import com.nhatnguyenba.musicplayer.data.remote.dto.ArtistDto
import com.nhatnguyenba.musicplayer.data.remote.dto.GenreDto
import com.nhatnguyenba.musicplayer.data.remote.dto.PlaylistDto
import com.nhatnguyenba.musicplayer.data.remote.dto.TrackDto
import com.nhatnguyenba.musicplayer.domain.models.Album
import com.nhatnguyenba.musicplayer.domain.models.Artist
import com.nhatnguyenba.musicplayer.domain.models.Genre
import com.nhatnguyenba.musicplayer.domain.models.Playlist
import com.nhatnguyenba.musicplayer.domain.models.Song
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun ArtistDto.toDomain(): Artist = Artist(
    id = this.id,
    name = this.name,
    imageUrl = this.pictureMedium
)

fun TrackDto.toDomain(): Song = Song(
    id = this.id,
    title = this.title,
    artist = this.artist.name,
    imageUrl = this.album.coverMedium,
    playBackUrl = this.preview
)

fun AlbumDto.toDomain(): Album = Album(
    id = this.id,
    title = this.title,
    artist = this.artist.name,
    imageUrl = this.coverMedium
)

fun PlaylistDto.toDomain(): Playlist = Playlist(
    id = this.id,
    title = this.title,
    author = this.user.name,
    description = "",
    imageUrl = this.pictureMedium,
    numberOfTracks = this.nbTracks.toInt()
)

fun GenreDto.toDomain(): Genre = Genre(
    id = this.id,
    name = this.name,
    imageUrl = this.pictureMedium
)

fun <T> Call<T>.toFlow(): Flow<T> = callbackFlow {

    val call = this@toFlow

    val callback = object : Callback<T> {

        override fun onResponse(call: Call<T>, response: Response<T>) {

            if (response.isSuccessful) {
                response.body()?.let {
                    trySend(it).isSuccess
                    close()
                } ?: close(Throwable("Response body is null"))
            } else {
                close(Throwable("HTTP ${response.code()}"))
            }
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            close(t)
        }
    }

    call.enqueue(callback)

    awaitClose {
        call.cancel() // cancel nếu flow bị cancel
    }
}.flowOn(Dispatchers.IO)
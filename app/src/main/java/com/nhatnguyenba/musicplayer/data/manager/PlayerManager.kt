package com.nhatnguyenba.musicplayer.data.manager

import android.net.Uri
import android.util.Log
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaController
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive
import javax.inject.Inject
import javax.inject.Singleton
import androidx.core.net.toUri

@Singleton
class PlayerManager @Inject constructor(
    controllerFuture: ListenableFuture<MediaController>
) {

    private var controller: MediaController? = null

    init {
        controllerFuture.addListener(
            {
                controller = controllerFuture.get()
            },
            MoreExecutors.directExecutor()
        )
    }

    fun play(url: String) {
        val mediaItem = MediaItem.fromUri(url.toUri())
        controller?.setMediaItem(mediaItem)
        controller?.prepare()
        controller?.play()
    }

    fun play() {
        controller?.play()
    }

    fun pause() {
        controller?.pause()
    }

    fun seekTo(position: Long) {
        controller?.seekTo(position)
    }

    fun observeDuration(): Flow<Long> = callbackFlow {
        var durationSet = false
        val listener = object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                if (playbackState == ExoPlayer.STATE_READY && !durationSet) {
                    trySend(controller?.getDuration() ?: 0)
                    durationSet = true
                    Log.d("NHAT", "Duration: ${controller?.getDuration()}")
                }
            }
        }

        controller?.addListener(listener)

        trySend(0L)

        awaitClose {
            controller?.removeListener(listener)
        }
    }

    fun observeCurrentPosition(): Flow<Long> =  flow {
        while (currentCoroutineContext().isActive) {
            emit(controller?.currentPosition ?: 0L)
            delay(500)
            Log.d("NHAT", "currentPosition: "+ controller?.currentPosition)
        }
    }.distinctUntilChanged()

    fun observeIsPlaying(): Flow<Boolean> = callbackFlow {

        val listener = object : Player.Listener {

            override fun onIsPlayingChanged(isPlaying: Boolean) {
                trySend(isPlaying)
            }
        }

        controller?.addListener(listener)

        controller?.isPlaying?.let { trySend(it) }

        awaitClose {
            controller?.removeListener(listener)
        }
    }
}
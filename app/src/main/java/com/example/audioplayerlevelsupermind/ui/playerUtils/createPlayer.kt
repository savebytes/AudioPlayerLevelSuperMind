package com.example.audioplayerlevelsupermind.ui.playerUtils

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer

fun createPlayer(context: Context): ExoPlayer {
    return ExoPlayer.Builder(context).build()
}

fun preparePlayer(player: ExoPlayer, url: String) {
    val mediaItem = MediaItem.fromUri(url)
    player.setMediaItem(mediaItem)
    player.prepare()
    player.play()
}

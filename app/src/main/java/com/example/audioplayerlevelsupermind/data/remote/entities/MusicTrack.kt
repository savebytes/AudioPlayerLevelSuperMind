package com.example.audioplayerlevelsupermind.data.remote.entities

import com.example.audioplayerlevelsupermind.data.local.entities.TrackEntry

data class MusicTrack(
    val id: Int,
    val audio_title: String,
    val artist: String,
    val song_poster: String,
    val audio_url: String
)

fun MusicTrack.asEntity(): TrackEntry = TrackEntry(
    id = id,
    audio_title = audio_title,
    artist = artist,
    audio_url = audio_url,
    song_poster = song_poster
)


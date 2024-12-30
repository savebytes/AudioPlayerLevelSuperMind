package com.example.audioplayerlevelsupermind.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "track_table")
data class TrackEntry(
    @PrimaryKey val id: Int,
    val audio_title: String,
    val artist: String,
    val song_poster: String,
    val audio_url: String,
    val filePath: String? = null
)

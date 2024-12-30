package com.example.audioplayerlevelsupermind.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.audioplayerlevelsupermind.R
import com.example.audioplayerlevelsupermind.data.local.TrackDao
import com.example.audioplayerlevelsupermind.data.local.entities.TrackEntry
import com.example.audioplayerlevelsupermind.data.remote.MusicApi
import com.example.audioplayerlevelsupermind.data.remote.entities.MusicTrack
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class TrackRepository(
    private val api: MusicApi,
    private val dao: TrackDao,
    private val context: Context
) {

    suspend fun fetchMusicTracks(): List<MusicTrack> {
        return api.getMusicTracks()
    }

    suspend fun fetchMusicTracksFromJSON(): List<MusicTrack> = withContext(Dispatchers.IO) {
        val json = context.resources.openRawResource(R.raw.data).bufferedReader()
            .use { it.readText() }
        val type = object : TypeToken<List<MusicTrack>>() {}.type
        Gson().fromJson(json, type)
    }

    suspend fun saveMusic(music: TrackEntry) {
        dao.insert(music)
    }

    suspend fun getMusic(): List<TrackEntry> {
        return dao.getAllTracks()
    }
}

package com.example.audioplayerlevelsupermind.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.audioplayerlevelsupermind.data.local.entities.TrackEntry
import com.example.audioplayerlevelsupermind.data.remote.entities.asEntity
import com.example.audioplayerlevelsupermind.data.repository.TrackRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TrackViewModel(private val repository: TrackRepository) : ViewModel() {

    private val _musicTracks = MutableStateFlow<List<TrackEntry>>(emptyList())
    val musicTracks: StateFlow<List<TrackEntry>> = _musicTracks


    fun fetchMusicTracks() {
        viewModelScope.launch {
            val music = repository.fetchMusicTracksFromJSON()
            music.forEach {
                repository.saveMusic(it.asEntity())
            }
            _musicTracks.value = repository.getMusic()
        }
    }


}
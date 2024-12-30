package com.example.audioplayerlevelsupermind

import android.R.color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.room.Room
import com.example.audioplayerlevelsupermind.data.local.AppDatabase
import com.example.audioplayerlevelsupermind.data.local.entities.TrackEntry
import com.example.audioplayerlevelsupermind.data.remote.MusicApi
import com.example.audioplayerlevelsupermind.data.repository.TrackRepository
import com.example.audioplayerlevelsupermind.ui.screens.AudioScreen
import com.example.audioplayerlevelsupermind.ui.screens.CurrentSongItem
import com.example.audioplayerlevelsupermind.ui.theme.AudioPlayerLevelSuperMindTheme
import com.example.audioplayerlevelsupermind.ui.viewmodel.TrackViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "tracks_db"
        ).build()

        val api = MusicApi.create()

        val repository = TrackRepository(api, db.trackDao(), this)

        val viewModel = TrackViewModel(repository)
        setContent {
            AudioPlayerLevelSuperMindTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(bottom = 100.dp)
                    ) {
                        AudioScreen(viewModel, this@MainActivity)
                    }
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        // Top content

                        Spacer(modifier = Modifier.weight(1f)) // Pushes the bottom box down

                        // Bottom container
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                        ) {
                            CurrentSongItem(
                                TrackEntry(
                                    id = 87,
                                    audio_title = "hellohg",
                                    artist = "doggy",
                                    song_poster = "milg",
                                    audio_url = "sad",
                                    null
                                ), onNextClick = {

                                },
                                onPlayClick = {

                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

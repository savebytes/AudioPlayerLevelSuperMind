package com.example.audioplayerlevelsupermind.ui.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.media3.exoplayer.ExoPlayer
import com.example.audioplayerlevelsupermind.data.local.entities.TrackEntry
import com.example.audioplayerlevelsupermind.ui.playerUtils.createPlayer
import com.example.audioplayerlevelsupermind.ui.playerUtils.preparePlayer
import com.example.audioplayerlevelsupermind.ui.viewmodel.TrackViewModel
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.audioplayerlevelsupermind.R
import com.example.audioplayerlevelsupermind.data.remote.entities.MusicTrack


@Composable
fun AudioScreen(viewModel: TrackViewModel, context: Context) {
    val musicTracks = viewModel.musicTracks.collectAsState()
    var currentPlayer by remember { mutableStateOf<ExoPlayer?>(null) }
    var currentTrack by remember { mutableStateOf<TrackEntry?>(null) }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(musicTracks.value) { track ->
            currentTrack = track
            MusicItem(
                track,
                onDownloadClick = {
                    // Handle download
                },
                onItemClick = {
                    // handle play
                    // Stop current playback if a player is active
                    currentPlayer?.release()

                    // Initialize a new player
                    val player = createPlayer(context)
                    val url = track.audio_url
                    if (url != null) {
                        preparePlayer(player, url)
                        currentPlayer = player
                    }
                }
            )
        }
    }
    LaunchedEffect(Unit) {
        viewModel.fetchMusicTracks()
    }
}


@Composable
fun MusicItem(
    track: TrackEntry,
    onDownloadClick: () -> Unit,
    onItemClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth(),
        onClick = onItemClick,
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(4.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .padding(2.dp),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = track.song_poster,
                    contentDescription = "Image from URL",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(8.dp),
                    placeholder = painterResource(R.drawable.audioicon),
                    error = painterResource(R.drawable.audioicon),
                )
            }
            Column(){
                Text(
                    text = track.audio_title,
                    style = MaterialTheme.typography.titleLarge
                )
                Row() {
                    Text(
                        text = track.artist,
                        fontWeight = FontWeight.Normal,
                        fontSize = 13.sp,
                        modifier = Modifier.padding(0.dp, 0.dp, 8.dp, 0.dp)
                    )
                    Text(
                        text = "|",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(8.dp, 0.dp)
                    )
                    Text(
                        text = "0:13",
                        fontWeight = FontWeight.Normal,
                        fontSize = 13.sp,
                        modifier = Modifier.padding(8.dp, 0.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(25.dp))
            Button(onClick = onDownloadClick) {
                Text("Download")
            }
        }
    }
}


@Composable
fun CurrentSongItem(
    track: TrackEntry,
    onNextClick: () -> Unit,
    onPlayClick: () -> Unit
    ) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .background(color = colorResource(R.color.white)),
        shape = RoundedCornerShape(12.dp),
    ) {
        Box(
            modifier = Modifier.background(colorResource(R.color.white))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                // Song Poster
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .padding(2.dp),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = "poster",
                        contentDescription = "Image from URL",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(64.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)),
                        placeholder = painterResource(R.drawable.audioicon),
                        error = painterResource(R.drawable.audioicon),
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))

                // Song Details
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = track.audio_title, // Song Title
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = "Sarah Kang", // Artist Name
                        style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))

                // Icons
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.play),
                        contentDescription = "Next Icon",
                        modifier = Modifier.size(32.dp).clickable {

                        },

                        )
                    Spacer(modifier = Modifier.width(12.dp))
                    Image(
                        painter = painterResource(R.drawable.next),
                        contentDescription = "Next Icon",
                        modifier = Modifier.size(32.dp),
                    )
                }
            }
        }
    }
}

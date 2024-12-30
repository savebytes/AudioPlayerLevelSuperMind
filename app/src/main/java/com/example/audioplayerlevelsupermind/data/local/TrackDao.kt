package com.example.audioplayerlevelsupermind.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.audioplayerlevelsupermind.data.local.entities.TrackEntry

@Dao
interface TrackDao {
    @Query("SELECT * FROM track_table")
    suspend fun getAllTracks(): List<TrackEntry>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(track: TrackEntry)

    @Query("SELECT * FROM track_table WHERE id = :id")
    suspend fun getTrackById(id: Int) : TrackEntry?
}



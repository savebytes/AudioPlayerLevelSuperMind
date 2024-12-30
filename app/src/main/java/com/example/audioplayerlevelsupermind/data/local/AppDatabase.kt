package com.example.audioplayerlevelsupermind.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.audioplayerlevelsupermind.data.local.entities.TrackEntry

@Database(entities = [TrackEntry::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun trackDao(): TrackDao
}



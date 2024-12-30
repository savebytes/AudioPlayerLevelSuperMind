package com.example.audioplayerlevelsupermind.data.remote

import com.example.audioplayerlevelsupermind.data.remote.entities.MusicTrack
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MusicApi {
    @GET("sample_music.json")
    suspend fun getMusicTracks(): List<MusicTrack>

    companion object {
        fun create(): MusicApi {
            return Retrofit.Builder()
                .baseUrl("https://your-json-url.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MusicApi::class.java)
        }
    }
}
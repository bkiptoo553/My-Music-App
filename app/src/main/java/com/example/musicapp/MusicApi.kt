package com.example.musicapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MusicApi {
    @Headers("x-rapidapi-key: 570ce57f7emsh88a557a3552794cp150d9cjsn23efa8dab553" +
    "x-rapidapi-host: deezerdevs-deezer.p.rapidapi.com")
    @GET("search")
    fun getData(@Query("q") query: String) : Call<MyData>
}
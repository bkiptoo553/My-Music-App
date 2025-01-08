package com.example.musicapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val tag = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var musicAdapter: RecyclerView
    private lateinit var songsButton: Button
    private lateinit var albumsButton: Button
    private lateinit var artistsButton: Button
    private lateinit var foldersButton: Button
    private lateinit var recent: Button
    private lateinit var favorites: Button
    private lateinit var playlist: Button
    private lateinit var shuffle: Button
    private lateinit var playAll: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MusicApi::class.java)

        val retrofitData = retrofitBuilder.getData("eminem")

        retrofitData.enqueue( object : Callback<MyData?> {
            override fun onResponse(p0: Call<MyData?>, p1: Response<MyData?>) {
                val dataList = p1.body()?.data

                if (dataList != null) {
                    musicAdapter.adapter = OnlineMusicAdapter(this@MainActivity, dataList)
                    musicAdapter.setHasFixedSize(true)
                    musicAdapter.layoutManager = LinearLayoutManager(this@MainActivity)
                    Log.d("TAG: onResponse", "onResponse: ${p1.body()}")
                } else {
                    Log.e("TAG: onFailure", "onFailure")
                }
            }


            override fun onFailure(p0: Call<MyData?>, p1: Throwable) {
                Log.d("TAG: onFailure", "onFailure" + p1.message)
            }

        })


        musicAdapter = findViewById(R.id.MusicRecycler)
        songsButton = findViewById(R.id.SongsBtn)
        artistsButton = findViewById(R.id.ArtistsBtn)
        albumsButton = findViewById(R.id.AlbumsBtn)
        foldersButton = findViewById(R.id.FolderBtn)
        recent = findViewById(R.id.recentBtn)
        favorites = findViewById(R.id.favoritesBtn)
        playlist = findViewById(R.id.playlistBtn)
        shuffle = findViewById(R.id.shuffleBtn)
        playAll = findViewById(R.id.playAllBtn)

        buttonActions()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }



    private fun buttonActions() {
        songsButton.setOnClickListener{
            Log.i(tag, "Song button clicked!")
        }
        albumsButton.setOnClickListener{
            Log.i(tag, "Album button clicked!")
        }
        artistsButton.setOnClickListener{
            Log.i(tag, "Artist button clicked!")
        }
        foldersButton.setOnClickListener{
            Log.i(tag, "Folder button clicked!")
        }
        recent.setOnClickListener{
            Log.i(tag, "Recent button clicked!")
        }
        favorites.setOnClickListener{
            Log.i(tag, "Favorites button clicked!")
        }
        playlist.setOnClickListener{
            Log.i(tag, "Playlist button clicked!")
        }
        playAll.setOnClickListener {
            Log.i(tag, "Play all button clicked!")

            Toast.makeText(this, "Playing all!", Toast.LENGTH_SHORT).show()
        }
        shuffle.setOnClickListener {
            Log.i(tag, "Shuffle button clicked!")

            Toast.makeText(this, "Shuffle is on!", Toast.LENGTH_SHORT).show()
        }
    }
}
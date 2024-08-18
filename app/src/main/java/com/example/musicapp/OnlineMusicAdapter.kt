package com.example.musicapp


import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class OnlineMusicAdapter(private val context: Context): RecyclerView.Adapter<OnlineMusicAdapter.MyViewHolder>() {
    class MyViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){


        // main variables
        private val coverImage : ImageView
        private val musicTitle : TextView
        private val musicAlbum : TextView
        private val playButton : ImageButton
        private val moreButton : ImageButton

        // song variables

        init {
            coverImage = itemView.findViewById(R.id.SongCover)
            musicTitle = itemView.findViewById(R.id.tvSongTitle)
            musicAlbum = itemView.findViewById(R.id.tvNameAlbum)
            moreButton = itemView.findViewById(R.id.moreBtn)
            playButton = itemView.findViewById(R.id.playBtn)
        }

        fun bind(position: Int, context: Context) {

            playButton.setOnClickListener{
                Log.i(tag, "Play button clicked!")
                val intent = Intent(context, OnlineMusicAdapter::class.java)
                startActivity(context, intent, null)

            }

            moreButton.setOnClickListener {
                Log.i(tag, "more or info Button Clicked!")
            }

        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val theItem = LayoutInflater.from(parent.context).inflate(R.layout.online_music_adapter, parent, false)
        return MyViewHolder(theItem)
    }

    override fun getItemCount() = 20

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(position, context)
    }
}

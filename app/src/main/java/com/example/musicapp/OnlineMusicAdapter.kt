package com.example.musicapp


import android.app.Activity
import android.media.MediaPlayer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class OnlineMusicAdapter(private val context: Activity, private val dataList: List<Data>):
    RecyclerView.Adapter<OnlineMusicAdapter.MyViewHolder>() {
    class MyViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){


        // main variables
        val coverImage : ImageView
        val musicTitle : TextView
        val musicAlbum : TextView
        val playButton : ImageButton
        val moreButton : ImageButton

        // song variables

        init {
            coverImage = itemView.findViewById(R.id.SongCover)
            musicTitle = itemView.findViewById(R.id.tvSongTitle)
            musicAlbum = itemView.findViewById(R.id.tvNameAlbum)
            moreButton = itemView.findViewById(R.id.moreBtn)
            playButton = itemView.findViewById(R.id.playBtn)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val theItem = LayoutInflater.from(parent.context).inflate(R.layout.online_music_adapter, parent, false)
        return MyViewHolder(theItem)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentData = dataList[position]
        val mediaPlayer= MediaPlayer.create(context, currentData.preview.toUri())

        holder.playButton.setOnClickListener{
            Log.i(tag, "Play button clicked!@$position")
            mediaPlayer.start()
        }

        holder.moreButton.setOnClickListener {
            Log.i(tag, "more or info Button Clicked!@$position")
            mediaPlayer.stop()
        }

        holder.musicTitle.text= currentData.title

        holder.musicAlbum.text= currentData.album.title

        Picasso.get().load(currentData.album.cover).into(holder.coverImage);
    }
}

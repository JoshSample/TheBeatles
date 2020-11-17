package com.example.thebeatles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapterSong : RecyclerView.Adapter<RecyclerAdapterSong.ViewHolder> {
    private var pos : Int = -1
    private var songs = SongFragment.getInstance().getSongs()

    constructor(pos : Int) : this()
    {
        this.pos = pos
    }

    constructor()
    {
    }


    public fun setPos(pos : Int)
    {
        this.pos = pos
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerAdapterSong.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.song_card_view, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return songs.size
    }

    override fun onBindViewHolder(holder: RecyclerAdapterSong.ViewHolder, position: Int) {
        holder.itemSongHeading.text = songs[position].getTitle()
        holder.itemWritten.text = songs[position].getWritten()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var itemSongHeading: TextView
        var itemWritten: TextView
        init {
            itemSongHeading = itemView.findViewById(R.id.songHeading)
            itemWritten = itemView.findViewById(R.id.written)
            var handler = Handler()
            itemView.setOnClickListener(handler)
        }

        inner class Handler() : View.OnClickListener {
            override fun onClick(v: View?) {
                val itemPosition = getLayoutPosition()
                //Get the navigation controller
                var navController = Navigation.findNavController(SongFragment.getInstance().requireView()!!)
                val bundle = Bundle()
                bundle.putInt("position", itemPosition)
                bundle.putString("song", songs[itemPosition].getTitle())
                navController.navigate(R.id.songToWeb,bundle)
            }
        }
    }
}
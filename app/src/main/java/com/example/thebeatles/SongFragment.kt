package com.example.thebeatles

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlinx.android.synthetic.main.fragment_song.*

class SongFragment : Fragment() {
    private var songs = mutableListOf<Songs>()
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapterSong.ViewHolder>? = null

    companion object {
        private var instance : SongFragment? = null
        public fun getInstance() : SongFragment {
            return instance!!
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_song, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // custom actionbar
        MainActivity.getInstance().setTitle("Songs")
        var colorDrawable = ColorDrawable(Color.parseColor("#009688"))
        MainActivity.getInstance().getSupportActionBar()?.setBackgroundDrawable(colorDrawable)

        var arguments = this.getArguments()
        var pos = arguments?.getInt("position")
        var album = arguments?.getString("album") + ".txt"

        var is1 = MainActivity.getInstance().assets.open(album)
        var reader1 = BufferedReader(InputStreamReader(is1))
        var lines1 = reader1.readLines()
        var arrayLines1 = lines1.toTypedArray()
        var allData1 = arrayOf<Array<String>>()
        for (i in 0..arrayLines1.size-1) {
            var array1 = arrayLines1[i].split("^")
            allData1 += array1.toTypedArray()
        }
        var songs = mutableListOf<Songs>()
        for (i in 0..allData1.size-1) {
            var song : Songs = Songs("", "")
            for (j in 0..allData1[i].size-1) {
                if (j == 0)
                    song.setTitle(allData1[i][j])
                if (j == 1)
                    song.setWritten(allData1[i][j])
            }
            songs.add(song)
        }
        setSongs(songs)
        reader1.close()

        layoutManager = LinearLayoutManager(MainActivity.getInstance())
        recycler_view2.layoutManager = layoutManager
        adapter = RecyclerAdapterSong(pos!!)
        recycler_view2.adapter = adapter
    }

    fun setSongs(songs: MutableList<Songs>) {
        this.songs = songs
    }

    fun getSongs() : MutableList<Songs>{
        return songs
    }
}
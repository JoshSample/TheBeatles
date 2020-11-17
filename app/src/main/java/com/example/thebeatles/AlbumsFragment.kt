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
import kotlinx.android.synthetic.main.fragment_albums.*


class AlbumsFragment : Fragment() {
    companion object {
        private var instance : AlbumsFragment? = null
        public fun getInstance() : AlbumsFragment {
            return instance!!
        }
    }

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_albums, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // custom actionbar
        MainActivity.getInstance().setTitle("Albums")
        var colorDrawable = ColorDrawable(Color.parseColor("#009688"))
        MainActivity.getInstance().getSupportActionBar()?.setBackgroundDrawable(colorDrawable)

        layoutManager = LinearLayoutManager(MainActivity.getInstance())
        albumsView.layoutManager = layoutManager
        adapter = RecyclerAdapter()
        albumsView.adapter = adapter
    }
    
}
package com.example.thebeatles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*

class MainActivity : AppCompatActivity() {
    private var albums = mutableListOf<Albums>()

    // companion object so we can get instances of MainActivity
    companion object {
        private var instance : MainActivity? = null

        public fun getInstance() : MainActivity {
            return instance!!
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        instance = this

        // read albums.txt so we can display albums in AlbumsFragment
        var is1 = this.assets.open("albums.txt")
        var reader1 = BufferedReader(InputStreamReader(is1))
        var lines1 = reader1.readLines()
        var arrayLines1 = lines1.toTypedArray()
        var allData1 = arrayOf<Array<String>>()
        for (i in 0..arrayLines1.size-1) {
            var array1 = arrayLines1[i].split("^")
            allData1 += array1.toTypedArray()
        }
        var albums = mutableListOf<Albums>()
        for (i in 0..allData1.size-1) {
            var album : Albums = Albums("", "", "", "")
            for (j in 0..allData1[i].size-1) {
                if (j == 0)
                    album.setTitle(allData1[i][j])
                if (j == 1)
                    album.setProduced(allData1[i][j])
                if (j == 2)
                    album.setDate(allData1[i][j])
                if (j == 3)
                    album.setImage(allData1[i][j])
            }
            albums.add(album)
        }
        setAlbum(albums)
        reader1.close()

        // setup navigation controller
        val navController = Navigation.findNavController(this, R.id.fragment)
        bottomNavigationView.setupWithNavController(navController)
    }

    fun setAlbum(albums: MutableList<Albums>) {
        this.albums = albums
    }

    fun getAlbum() : MutableList<Albums>{
        return albums
    }
}
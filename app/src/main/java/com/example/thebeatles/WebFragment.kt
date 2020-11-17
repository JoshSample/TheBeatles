package com.example.thebeatles

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AlertDialog
import org.json.JSONObject
import java.net.URL
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_web.*

// UI thread helper class, will update the webView with YouTube video obtained from JSON parsing
class UIThreadHelper : Thread {
    private var video : String = ""

    constructor(url : String) {
        this.video = url
    }

    override public fun run() {
        //Update the webView
        var web = MainActivity.getInstance().findViewById<WebView>(R.id.webView)
        val settings = web.getSettings()
        settings.setJavaScriptEnabled(true)
        settings.setDomStorageEnabled(true)
        settings.setMinimumFontSize(10)
        settings.setLoadWithOverviewMode(true)
        settings.setUseWideViewPort(true)
        settings.setBuiltInZoomControls(true)
        settings.setDisplayZoomControls(false)
        web.setVerticalScrollBarEnabled(false)
        settings.setDomStorageEnabled(true)
        web.setWebViewClient(WebViewClient())
        var str = "https://www.youtube.com/watch?v=" + video
        web.loadUrl(str)
    }
}

// Helper class for the thread
class Helper : Runnable {
    private var song : String = ""
    private var artist : String = ""
    private var url : String  = ""

    constructor(url : String, artist : String, song : String) {
        this.song = song
        this.artist = artist
        this.url = url
    }


    override public fun run() {
        val data = URL(url).readText()  //Read all the data at this URL
        var json = JSONObject(data)
        var items = json.getJSONArray("items")
        var titles = ArrayList<String>()
        var videos = ArrayList<String>()

        for (i in 0 until items.length()) {
            var videoObject = items.getJSONObject(i)
            var idDict = videoObject.getJSONObject("id")
            var videoId = idDict.getString("videoId")
            var snippetDict = videoObject.getJSONObject("snippet")
            var title =  snippetDict.getString("title")
            titles.add(title)
            videos.add(videoId)
        }

        //iterate through each title and find which video has both artist and song
        var selected_video : String = ""
        var selected_title : String = ""
        for (i in 0..videos.size-1) {
            if (titles[i].contains(artist) && titles[i].contains(song)) {
                selected_video = videos[i]
                selected_title = titles[i]
                break
            }
        }
        if (selected_video == "") {
            val dialogBuilder = WebFragment.getInstance().context?.let { AlertDialog.Builder(it) }

            // set message of alert dialog
            if (dialogBuilder != null) {
                dialogBuilder.setMessage("No video found, please hit back button")
            }

            // create dialog box
            val alert = dialogBuilder?.create()
            // set title for alert dialog box
            if (alert != null) {
                alert.setTitle("No Video Found")
                alert.show()
            }
        }

        var helper1 = UIThreadHelper(selected_video)
        MainActivity.getInstance().runOnUiThread(helper1)
    }
}


class WebFragment : Fragment() {
    companion object {
        private var instance : WebFragment? = null
        public fun getInstance() : WebFragment {
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
        return inflater.inflate(R.layout.fragment_web, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // get arguments from SongFragment
        var arguments = this.arguments
        var song = arguments?.getString("song")
        var songCopy = song

        // set up data for JSON parsing
        if (song != null) {
            song = song.replace(" ", "+")
            //Set the artist
            var artist = "The Beatles"
            artist = artist.replace(" ","+")
            var origArtist = "The Beatles"
            //Encode search for YouTube
            val keywords = artist + "+" +  song
            val max = 50

            val string = "https://www.googleapis.com/youtube/v3/search?part=snippet&q=$keywords&order=viewCount&maxResults=$max&type=video&videoCategory=Music&key=AIzaSyDtzKWgA0ne39VD_-i0oJwCd4WOdFKZy4I"

            var helper = songCopy?.let { Helper(string, origArtist, it) }
            var thread = Thread(helper)
            thread.start()
        }

    }
}
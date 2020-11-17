package com.example.thebeatles

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView


class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // custom actionbar
        MainActivity.getInstance().setTitle("Home")
        var colorDrawable = ColorDrawable(Color.parseColor("#009688"))
        MainActivity.getInstance().getSupportActionBar()?.setBackgroundDrawable(colorDrawable)

        // set up slidshow
        var imageView = MainActivity.getInstance().findViewById<ImageView>(R.id.imageView)
        var context = imageView.context
        var image = arrayOf<Int>(MainActivity.getInstance().resources.getIdentifier("intro1","drawable",context.packageName),
            MainActivity.getInstance().resources.getIdentifier("pleasepleaseme","drawable",context.packageName),
            MainActivity.getInstance().resources.getIdentifier("with_the_beatles","drawable",context.packageName),
            MainActivity.getInstance().resources.getIdentifier("harddaysnight", "drawable", context.packageName),
            MainActivity.getInstance().resources.getIdentifier("beatlesforsale", "drawable", context.packageName),
            MainActivity.getInstance().resources.getIdentifier("help", "drawable", context.packageName),
            MainActivity.getInstance().resources.getIdentifier("rubber_soul", "drawable", context.packageName),
            MainActivity.getInstance().resources.getIdentifier("revolver", "drawable", context.packageName),
            MainActivity.getInstance().resources.getIdentifier("sgt_pepper", "drawable", context.packageName),
            MainActivity.getInstance().resources.getIdentifier("white", "drawable", context.packageName),
            MainActivity.getInstance().resources.getIdentifier("yellowsubmarine", "drawable", context.packageName),
            MainActivity.getInstance().resources.getIdentifier("abbeyroad", "drawable", context.packageName),
            MainActivity.getInstance().resources.getIdentifier("letitbe", "drawable", context.packageName),
            MainActivity.getInstance().resources.getIdentifier("magicalmysterytour", "drawable", context.packageName),
            MainActivity.getInstance().resources.getIdentifier("pastmastersvolume1", "drawable", context.packageName),
            MainActivity.getInstance().resources.getIdentifier("pastmastersvolume2", "drawable", context.packageName))

        // slideshow of the album images
        val handler = Handler()
        var i = 0
        handler.postDelayed(object : Runnable {
            override fun run() {
                imageView.setImageResource(image.get(i))
                handler.postDelayed(this, 3000)
                i++
                if (i > image.size-1)
                    i = 0
            }
        }, 3000)
    }
}
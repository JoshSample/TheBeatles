package com.example.thebeatles

// class to make Song objects, data obtained from which button is pressed in SongFragment
class Songs {
    private var title : String = ""
    private var written : String = ""

    constructor(title: String, written: String) {
        this.title = title
        this.written = written
    }

    public fun setTitle(title : String) {
        this.title = title
    }

    public fun getTitle() : String {
        return title
    }

    public fun setWritten(written : String) {
        this.written = written
    }

    public fun getWritten() : String {
        return written
    }
}
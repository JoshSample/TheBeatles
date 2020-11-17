package com.example.thebeatles

// class to make an Albums object. Data obtained from reading corresponding file
class Albums {
    private var title : String = ""
    private var produced : String = ""
    private var date : String = ""
    private var image : String = ""

    constructor(title : String, produced : String, date : String, image : String) {
        this.title = title
        this.produced = produced
        this.date = date
        this.image = image
    }

    public fun setTitle(title : String) {
        this.title = title
    }

    public fun getTitle() : String {
        return title
    }

    public fun setProduced(produced: String) {
        this.produced = produced
    }

    public fun getProduced() : String {
        return produced
    }

    public fun setDate(date : String) {
        this.date = date
    }

    public fun getDate() : String {
        return date
    }

    public fun setImage(image : String) {
        this.image = image
    }

    public fun getImage() : String {
        return image
    }
}
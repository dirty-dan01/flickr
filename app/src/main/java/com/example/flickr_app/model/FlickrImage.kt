package com.example.flickr_app.model

data class FlickrImage(
    val title: String,
    val link: String,
    val media: FlickrMedia,
    val description: String,
    val published: String,
    val author: String,
    val tags: String
)
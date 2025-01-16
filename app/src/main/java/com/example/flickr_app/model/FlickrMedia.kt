package com.example.flickr_app.model

import com.google.gson.annotations.SerializedName

data class FlickrMedia(
    @SerializedName("m")
    val imageUrl: String
)
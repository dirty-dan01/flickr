package com.example.flickr_app.model

import com.google.gson.annotations.SerializedName

data class FlickrImageResponse(
    val title: String = "",
    val link: String = "",
    val description: String = "",
    @SerializedName("items")
    val images: List<FlickrImage> = emptyList()
)
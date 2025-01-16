package com.example.flickr_app.repository

import com.example.flickr_app.model.FlickrImage
import com.example.flickr_app.model.FlickrImageResponse
import com.example.flickr_app.model.FlickrMedia
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface FlickrImageRepository {

    fun cacheImage(flickrImage: FlickrImage)

    fun fetchCachedImage(): FlickrImage? = null

    suspend fun fetchImages(searchQuery: String): FlickrImageResponse
}

class FlickrImageRepositoryImpl(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : FlickrImageRepository {

    private var flickrImage: FlickrImage? = null

    override fun cacheImage(flickrImage: FlickrImage) {
        this.flickrImage = flickrImage
    }

    override fun fetchCachedImage(): FlickrImage? = flickrImage

    override suspend fun fetchImages(searchQuery: String): FlickrImageResponse = withContext(dispatcher) {
        return@withContext FlickrImageResponse(images = listOf(
            FlickrImage(
                title = "IMG_0999",
                link = "https://www.flickr.com/photos/macspud/54255022494/",
                media = FlickrMedia(imageUrl = "https://live.staticflickr.com/65535/54255022494_0ba648ff48_m.jpg"),
                description = "",
                published = "2025-01-08T19:43:32Z",
                author = "Daniel",
                tags = ""
            )
        ))
    }

}
package com.example.flickr_app.dependencyinjection

import com.example.flickr_app.repository.FlickrImageRepository
import com.example.flickr_app.repository.FlickrImageRepositoryImpl
import com.example.flickr_app.usecase.SearchImagesUseCase
import com.example.flickr_app.usecase.SearchImagesUseCaseImpl

object Dependencies {

    val imagesRepo: FlickrImageRepository by lazy {
        FlickrImageRepositoryImpl()
    }

    val searchImageUseCase: SearchImagesUseCase by lazy {
        SearchImagesUseCaseImpl(imagesRepo)
    }
}
package com.example.flickr_app.usecase

import com.example.flickr_app.model.FlickrImage
import com.example.flickr_app.model.NetworkResponse
import com.example.flickr_app.repository.FlickrImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface SearchImagesUseCase {

    operator fun invoke(query: String): Flow<NetworkResponse<List<FlickrImage>>>
}

class SearchImagesUseCaseImpl(
    private val repo: FlickrImageRepository
) : SearchImagesUseCase {

    override fun invoke(query: String): Flow<NetworkResponse<List<FlickrImage>>> = flow {
        emit(NetworkResponse.Loading())
        try {
            val imageResponse = repo.fetchImages(query)
            emit(NetworkResponse.Success(imageResponse.images))
        } catch (e: Exception) {
            emit(NetworkResponse.Error(e))
        }
    }

}
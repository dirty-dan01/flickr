package com.example.flickr_app.model

sealed class NetworkResponse<T> {
    class Loading<T> : NetworkResponse<T>()
    data class Success<T>(val data: T) : NetworkResponse<T>()
    data class Error<T>(val error: Throwable? = null) : NetworkResponse<T>()
}
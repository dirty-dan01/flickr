package com.example.flickr_app.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flickr_app.R
import com.example.flickr_app.dependencyinjection.Dependencies
import com.example.flickr_app.model.FlickrImage
import com.example.flickr_app.model.NetworkResponse
import com.example.flickr_app.usecase.SearchImagesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(

) : ViewModel() {
    private val searchImageUseCase: SearchImagesUseCase by lazy { Dependencies.searchImageUseCase }
    private val _state = MutableStateFlow<HomeUIState>(HomeUIState.Initial)
    val state: StateFlow<HomeUIState> = _state

    fun searchImages(query: String) {
        viewModelScope.launch {
            val searchFlow = searchImageUseCase.invoke(query)
            searchFlow.collectLatest {
                when(it) {
                    is NetworkResponse.Error -> _state.emit(HomeUIState.ImagesError(R.string.search_error))
                    is NetworkResponse.Loading -> _state.emit(HomeUIState.Loading)
                    is NetworkResponse.Success -> _state.emit(HomeUIState.ImagesLoaded(it.data))
                }
            }
        }
    }

}

sealed class HomeUIState {
    object Initial : HomeUIState()
    object Loading : HomeUIState()
    data class ImagesLoaded(val images: List<FlickrImage>) : HomeUIState()
    data class ImagesError(val error: Int) : HomeUIState()
}
package com.example.flickr_app.ui.home

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flickr_app.ui.component.SearchBar

@Composable
fun HomeScreen() {
    val homeViewModel: HomeViewModel = viewModel()
    val state by homeViewModel.state.collectAsState()
    val context = LocalContext.current

    Column {
        SearchBar {
            homeViewModel.searchImages(it)
        }
        when(state) {
            is HomeUIState.ImagesError -> Toast.makeText(context, (state as HomeUIState.ImagesError).error, Toast.LENGTH_LONG).show()
            is HomeUIState.ImagesLoaded ->  Toast.makeText(context, "images loaded", Toast.LENGTH_LONG).show()
            HomeUIState.Initial -> Text("Please enter a query to search images", modifier = Modifier.padding(16.dp))
            HomeUIState.Loading -> {
                Box(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }
}
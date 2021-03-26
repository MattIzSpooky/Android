package com.avans.assessment.viewmodels

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.avans.assessment.services.NetworkImageService

class NetworkImageViewModel(ctx: Context) : ViewModel() {
    private val networkImageService = NetworkImageService(ctx)

    var image: Bitmap? by mutableStateOf(null)
        private set

    var isFetching by mutableStateOf(false)

    fun loadImage(url: String) {
        isFetching = true

        networkImageService.fetchImage(url, ImageView.ScaleType.CENTER_CROP) {
            image = it

            isFetching = false
        }
    }
}
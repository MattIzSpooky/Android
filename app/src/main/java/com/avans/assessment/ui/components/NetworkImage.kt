package com.avans.assessment.ui.components

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import com.avans.assessment.viewmodels.NetworkImageViewModel

@Composable
fun NetworkImage(context: Context, url: String, modifier: Modifier = Modifier) {
    val networkImageViewModel = NetworkImageViewModel(context)

    networkImageViewModel.loadImage(url)

    ImageContainer(networkImageViewModel, url, modifier)
}

@Composable
private fun ImageContainer(networkImageViewModel: NetworkImageViewModel, url: String, modifier: Modifier) {
    if (networkImageViewModel.isFetching) {
        CircularProgressIndicator(modifier)
        return
    }

    if (networkImageViewModel.image == null) {
        Text("Could not load: $url")
        return
    }

    networkImageViewModel.image?.also {
        Image(it.asImageBitmap(), url, modifier)
    }
}
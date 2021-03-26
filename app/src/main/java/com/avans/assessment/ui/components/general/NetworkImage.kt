package com.avans.assessment.ui.components.general

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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

    if (networkImageViewModel.image == null && networkImageViewModel.error != null) {
        Text("X")
        return
    }

    networkImageViewModel.image?.also {
        Image(it.asImageBitmap(), url, modifier)
    }
}
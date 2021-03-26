package com.avans.assessment.services

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.android.volley.toolbox.ImageRequest
import com.avans.assessment.exceptions.NoInternetException
import com.avans.assessment.network.ApiClient
import com.avans.assessment.network.NetworkStatus
import java.lang.NullPointerException

class NetworkImageService(ctx: Context) : BaseService(ctx) {
    private val networkStatus = NetworkStatus(ctx)

    @Throws(NullPointerException::class, NoInternetException::class)
    fun fetchImage(
        url: String,
        cropType: ImageView.ScaleType,
        onResponse: (result: Bitmap) -> Unit,
        onError: (error: String) -> Unit
    ) {
        if (!networkStatus.isConnected()) throw NoInternetException()

        val imageRequest = ImageRequest(url, { response ->
            onResponse(response)
        }, 0, 0, cropType, null,
            {
                onError(it.message ?: "No message provided")
            })

        ApiClient.getInstance(retrieveContextOrThrow()).addToRequestQueue(imageRequest)
    }
}

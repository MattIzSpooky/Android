package com.avans.assessment.services

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.android.volley.toolbox.ImageRequest
import java.lang.ref.WeakReference

class NetworkImageService(ctx: Context ) {
    private val context = WeakReference(ctx)

    fun fetchImage(url: String, cropType: ImageView.ScaleType, callback: (result: Bitmap) -> Unit) {
        val imageRequest = ImageRequest(url, { response ->
            callback(response)
        }, 0, 0, cropType, null,
            {
                print("Something went wrong")
            })

        this.context.get()?.let { ApiClient.getInstance(it).addToRequestQueue(imageRequest) }
    }

}
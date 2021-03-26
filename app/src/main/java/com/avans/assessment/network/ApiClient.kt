package com.avans.assessment.network

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class ApiClient constructor(context: Context) {
    companion object {
        private const val BASE_URL = "https://api.punkapi.com/v2/beers"

        @Volatile
        private var INSTANCE: ApiClient? = null
        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: ApiClient(context).also {
                    INSTANCE = it
                }
            }
    }

    private val requestQueue: RequestQueue by lazy {
        // applicationContext is key, it keeps you from leaking the
        // Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }

    fun <T> addToRequestQueue(req: Request<T>) {
        requestQueue.add(req)
    }

    fun createUrl(url: String): String {
        return BASE_URL + url
    }

    inline fun <reified T> createGsonRequest(
        url: String,
        crossinline onResponse: (result: T) -> Unit,
        crossinline onError: (result: String) -> Unit
    ): GsonRequest<T> {
        return GsonRequest(url, T::class.java, null,
            { response ->
                onResponse(response)
            },
            {
                onError(it.message ?: "No message provided.")
            })
    }
}
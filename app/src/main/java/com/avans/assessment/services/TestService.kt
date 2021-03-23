package com.avans.assessment.services

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.avans.assessment.ApiClient
import java.lang.ref.WeakReference

class TestService(ctx: Context) {
    private val context = WeakReference(ctx)

    fun fetchData(callback: (result: String) -> Unit) {
        val stringRequest = StringRequest(
            Request.Method.GET, "https://www.google.com/",
            { response ->
               callback(response)
            },
            { print("kaput") })

        this.context.get()?.let { ApiClient.getInstance(it).addToRequestQueue(stringRequest) }
    }
}
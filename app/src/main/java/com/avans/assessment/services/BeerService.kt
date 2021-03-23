package com.avans.assessment.services

import android.content.Context
import com.avans.assessment.models.Beer
import com.avans.assessment.utils.GsonRequest
import java.lang.ref.WeakReference

class BeerService(ctx: Context) {
    private val context = WeakReference(ctx)

    fun fetchBeers(callback: (result: List<Beer>) -> Unit) {
        val stringRequest = GsonRequest(
            "https://api.punkapi.com/v2/beers", Array<Beer>::class.java,null,
            { response ->
                callback(response.toList())
            },
            {
                print("kaput")
            })

        this.context.get()?.let { ApiClient.getInstance(it).addToRequestQueue(stringRequest) }
    }
}
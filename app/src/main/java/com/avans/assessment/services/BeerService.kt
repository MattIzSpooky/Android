package com.avans.assessment.services

import android.content.Context
import com.avans.assessment.models.Beer
import com.avans.assessment.utils.GsonRequest
import java.lang.ref.WeakReference

class BeerService(ctx: Context) {
    private val context = WeakReference(ctx)

    fun fetchBeers(page: Int, perPage: Int, callback: (result: List<Beer>) -> Unit) {
        val stringRequest = GsonRequest(
            "https://api.punkapi.com/v2/beers?page=$page&per_page=$perPage", Array<Beer>::class.java,null,
            { response ->
                callback(response.toList())
            },
            {
                print("Something went wrong")
            })

        this.context.get()?.let { ApiClient.getInstance(it).addToRequestQueue(stringRequest) }
    }

    fun fetchBeer(id: String, callback: (result: Beer) -> Unit) {
        val stringRequest = GsonRequest(
            "https://api.punkapi.com/v2/beers/$id", Array<Beer>::class.java,null,
            { response ->
                callback(response[0])
            },
            {
                print("Something went wrong")
            })

        this.context.get()?.let { ApiClient.getInstance(it).addToRequestQueue(stringRequest) }
    }
}
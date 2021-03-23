package com.avans.assessment

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import java.lang.ref.WeakReference

class TestViewModel(ctx: Context) : ViewModel() {
    private val _data = MutableLiveData("")
    val data: LiveData<String> = _data

    private val context = WeakReference(ctx)

    fun loadData() {
        val stringRequest = StringRequest(
            Request.Method.GET, "https://www.google.com/",
            { response ->
                this._data.value = response.take(500)
            },
            { print("kaput") })

        this.context.get()?.let { ApiClient.getInstance(it).addToRequestQueue(stringRequest) }
    }
}

package com.avans.assessment.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.avans.assessment.services.TestService

class TestViewModel(ctx: Context) : ViewModel() {
    private val _data = MutableLiveData("")
    val data: LiveData<String> = _data
    private val testService = TestService(ctx)


    fun loadData() {
        testService.fetchData {
            this._data.value = it.take(500)
        }
    }
}

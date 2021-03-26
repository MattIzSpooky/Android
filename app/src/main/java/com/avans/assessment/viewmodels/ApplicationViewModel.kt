package com.avans.assessment.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

open class ApplicationViewModel() : ViewModel() {
    var error: String? by mutableStateOf(null)
        protected set
}

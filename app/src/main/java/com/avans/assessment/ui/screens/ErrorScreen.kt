package com.avans.assessment.ui.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.avans.assessment.ui.components.general.Centered

@Composable
fun ErrorScreen(error: String) {
    Centered {
        Text("Error: $error")
    }
}

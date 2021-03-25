package com.avans.assessment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.avans.assessment.ui.screens.SearchScreen
import com.avans.assessment.ui.theme.AvansandroidassessmentTheme

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AvansandroidassessmentTheme {
                Surface(color = MaterialTheme.colors.background) {
                   SearchScreen(applicationContext, intent.getStringExtra(Intent.EXTRA_TEXT) ?: "")
                }
            }
        }
    }
}
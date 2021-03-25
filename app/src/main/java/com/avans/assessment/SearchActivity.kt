package com.avans.assessment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import com.avans.assessment.ui.theme.AvansandroidassessmentTheme

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val searchText = intent.getStringExtra(Intent.EXTRA_TEXT) ?: "No search text"

        setContent {
            AvansandroidassessmentTheme {
                Surface(color = MaterialTheme.colors.background) {
                   Text(searchText)
                }
            }
        }
    }
}
package com.avans.assessment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.avans.assessment.ui.screens.RandomScreen

class RandomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random)

        supportFragmentManager.beginTransaction()
            .replace(R.id.random_container, RandomScreen())
            .commit()
    }
}
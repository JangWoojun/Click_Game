package com.example.clickgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MaxScoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_max_score)

        val maxScore = intent.getStringExtra("maxScore")?.toInt()

    }
}
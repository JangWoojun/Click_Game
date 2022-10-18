package com.example.clickgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MultiMaxScoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi_max_score)

        val maxScore1 = intent.getStringExtra("maxScore1")
        val maxScore2 = intent.getStringExtra("maxScore2")

    }
}
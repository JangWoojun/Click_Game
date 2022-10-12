package com.example.clickgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MaxScoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_max_score)

        val maxScore = intent.getStringExtra("maxScore")

        val maxMs = findViewById<TextView>(R.id.maxMS)
        maxMs.text = "${maxScore}ms"

    }
}
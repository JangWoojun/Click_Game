package com.example.clickgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MaxScoreActivity : AppCompatActivity() {
    private var backPressedTime : Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_max_score)

        val maxScore = intent.getStringExtra("maxScore")

        val maxMs = findViewById<TextView>(R.id.maxMS)
        maxMs.text = "${maxScore}ms"

    }
    override fun onBackPressed() {

        if (System.currentTimeMillis() - backPressedTime < 2000) {
            finish()
            return
        }

        backPressedTime = System.currentTimeMillis()
    }
}
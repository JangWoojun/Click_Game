package com.example.clickgame

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MaxScoreActivity : AppCompatActivity() {
    private var backPressedTime : Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_max_score)

        val maxScore = intent.getStringExtra("maxScore")

        val maxMs = findViewById<TextView>(R.id.maxMS)
        maxMs.text = getString(R.string.maxMs,maxScore)

    }
    override fun onBackPressed() {

        if (System.currentTimeMillis() - backPressedTime < 2000) {
            finish()
            return
        }

        backPressedTime = System.currentTimeMillis()
    }
}
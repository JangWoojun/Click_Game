package com.speedtest.clickgame

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.speedtest.clickgame.databinding.ActivityMaxScoreBinding

class MaxScoreActivity : AppCompatActivity() {
    private var backPressedTime : Long = 0
    private lateinit var binding: ActivityMaxScoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_max_score)
        binding = ActivityMaxScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MobileAds.initialize(this) {}

        val mAdView = findViewById<AdView>(R.id.banner)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        val maxScore = intent.getStringExtra("maxScore")

            binding.maxMS.text = getString(R.string.maxMs,maxScore)

        binding.restartBtn.setOnClickListener {
            val intent = Intent(this,GameActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            overridePendingTransition(R.anim.vertical_enter, R.anim.none)
        }

        binding.homeBtn.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            overridePendingTransition(R.anim.vertical_enter, R.anim.none)
        }

    }
    override fun onBackPressed() {

        if (System.currentTimeMillis() - backPressedTime < 2000) {
            finish()
            return
        }

        backPressedTime = System.currentTimeMillis()
    }
}
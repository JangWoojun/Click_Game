package com.speedtest.clickgame

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.speedtest.clickgame.databinding.ActivityMultiMaxScoreBinding

class MultiMaxScoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMultiMaxScoreBinding
    private var backPressedTime : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi_max_score)
        binding = ActivityMultiMaxScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MobileAds.initialize(this) {}

        val mAdView = findViewById<AdView>(R.id.banner)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        val maxScore1 = intent.getStringExtra("maxScore1")!!.toInt()
        val maxScore2 = intent.getStringExtra("maxScore2")!!.toInt()

        if (maxScore1<maxScore2){
            binding.winner.text = getString(R.string.PLAYER1)
            binding.maxMS.text = getString(R.string.maxMs,maxScore1.toString())
        }
        else {
            binding.winner.text = getString(R.string.PLAYER2)
            binding.maxMS.text = getString(R.string.maxMs,maxScore2.toString())
        }

        binding.restartBtn.setOnClickListener {
            val intent = Intent(this,MultiActivity::class.java)
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
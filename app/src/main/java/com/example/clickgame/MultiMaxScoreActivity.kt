package com.example.clickgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.clickgame.databinding.ActivityMaxScoreBinding
import com.example.clickgame.databinding.ActivityMultiMaxScoreBinding

class MultiMaxScoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMultiMaxScoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi_max_score)
        binding = ActivityMultiMaxScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val maxScore1 = intent.getStringExtra("maxScore1")!!.toInt()
        val maxScore2 = intent.getStringExtra("maxScore2")!!.toInt()

        if (maxScore1<maxScore2){
            binding.maxMS.text = getString(R.string.maxMs,maxScore1.toString())
        }
        else {
            binding.maxMS.text = getString(R.string.maxMs,maxScore2.toString())
        }

        binding.restartBtn.setOnClickListener {
            val intent = Intent(this,MultiActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }

        binding.homeBtn.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }

    }
}
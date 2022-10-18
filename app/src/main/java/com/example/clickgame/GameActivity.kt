package com.example.clickgame

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.clickgame.databinding.ActivityGameBinding
import kotlin.concurrent.thread

class GameActivity : AppCompatActivity() {
    private var time = 0
    private var totalTime = 0
    private var started = false
    private var chk = false
    private var num = 0
    private var backPressedTime : Long = 0
    private lateinit var binding:ActivityGameBinding

    private var i = 1

    private val handler = Handler(Looper.getMainLooper())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        num = (2000..4000).random()

        handler.postDelayed({

            start()

        }, num.toLong())

        binding.clickBtn.setOnClickListener {
            if (chk){
                if(i==5){
                    val intent = Intent(this,MaxScoreActivity::class.java)
                    totalTime/=5
                    intent.putExtra("maxScore",totalTime.toString())
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    overridePendingTransition(0, 0)
                }
                stop(i)
                i++
                chk =false
                num = (3000..5000).random()
                handler.postDelayed({

                    start()

                }, num.toLong())
            }
            else {
                binding.resetBtn.visibility = View.VISIBLE
                binding.soon1.visibility = View.VISIBLE
                binding.soon2.visibility = View.VISIBLE
                binding.timeCheck.visibility = View.INVISIBLE
                binding.waitText.visibility = View.INVISIBLE
                binding.clickText.visibility = View.INVISIBLE
                binding.clickBtn.visibility = View.INVISIBLE

                handler.removeCallbacksAndMessages(null)
                time = 0
                totalTime = 0
                i = 0
                binding.gameLayout.setBackgroundColor(Color.parseColor("#a9cbd7"))
                binding.count.text = getString(R.string.count,i)

                binding.resetBtn.setOnClickListener {
                    reset()
                    i++
                }
            }
        }

    }

    private fun start(){

        binding.soon1.visibility = View.INVISIBLE
        binding.soon2.visibility = View.INVISIBLE
        binding.resetBtn.visibility = View.INVISIBLE
        binding.gameLayout.setBackgroundColor(Color.parseColor("#2dd12d"))
        binding.timeCheck.visibility = View.INVISIBLE
        binding.waitText.visibility = View.INVISIBLE
        binding.clickText.visibility = View.VISIBLE
        binding.clickBtn.text = getString(R.string.Click)
        binding.clickBtn.visibility = View.VISIBLE

        chk = true
        started = true
        thread(start=true) {

            while (true){
                if (!started) break

                Thread.sleep(1)
                time+=1
            }

        }
    }

    private fun stop(i:Int){

        started=false

        binding.gameLayout.setBackgroundColor(Color.parseColor("#c0102a"))
        binding.clickBtn.text = getString(R.string.Ready)
        binding.count.text = getString(R.string.count,i)
        binding.timeCheck.text = getString(R.string.timeCheck,time)
        binding.timeCheck.visibility = View.VISIBLE
        binding.waitText.visibility = View.VISIBLE
        binding.clickText.visibility = View.INVISIBLE
        binding.waitText.text = getString(R.string.game_guidance)

        totalTime+=time
        time=0
    }
    private fun reset(){

        binding.resetBtn.visibility = View.INVISIBLE
        binding.soon1.visibility = View.INVISIBLE
        binding.soon2.visibility = View.INVISIBLE
        binding.clickBtn.visibility = View.VISIBLE
        binding.waitText.visibility = View.VISIBLE

        binding.gameLayout.setBackgroundColor(Color.parseColor("#c0102a"))
        time = 0
        totalTime = 0
        i = 0

        val num = (3000..5000).random()

        handler.postDelayed({

            start()

        }, num.toLong())

    }

    override fun onBackPressed() {

        if (System.currentTimeMillis() - backPressedTime < 2000) {
            finish()
            return
        }
        backPressedTime = System.currentTimeMillis()
    }

}
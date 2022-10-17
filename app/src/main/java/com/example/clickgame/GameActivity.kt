package com.example.clickgame

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.concurrent.thread

class GameActivity : AppCompatActivity() {
    private var time = 0
    private var totalTime = 0
    private var started = false
    private var chk = false
    private var num = 0
    private var backPressedTime : Long = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val clickBtn = findViewById<Button>(R.id.clickBtn)

        num = (500..5000).random()

        var i = 1

        Handler(Looper.getMainLooper()).postDelayed({

            start()

        }, num.toLong())

        clickBtn.setOnClickListener {
            if (chk){
                if(i==5){
                    val intent = Intent(this,MaxScoreActivity::class.java)
                    totalTime/=5
                    intent.putExtra("maxScore",totalTime.toString())
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }

                stop(i)
                i++

                chk =false

                val num = (500..3500).random()

                Handler(Looper.getMainLooper()).postDelayed({

                    start()

                }, num.toLong())
            }
        }

    }

    private fun start(){

        val gameLayout = findViewById<ConstraintLayout>(R.id.gameLayout)
        val clickBtn = findViewById<Button>(R.id.clickBtn)
        val timeCheck = findViewById<TextView>(R.id.timeCheck)
        val waitText = findViewById<TextView>(R.id.waitText)
        val clickText = findViewById<TextView>(R.id.clickText)
        chk = true
        started = true
        thread(start=true) {

            while (true){
                if (!started) break

                Thread.sleep(1)
                time+=1
            }

        }
        gameLayout.setBackgroundColor(Color.parseColor("#2dd12d"))
        timeCheck.visibility = View.INVISIBLE
        waitText.visibility = View.INVISIBLE
        clickText.visibility = View.VISIBLE
        clickBtn.text = getString(R.string.Click)
    }

    private fun stop(i:Int){
        val gameLayout = findViewById<ConstraintLayout>(R.id.gameLayout)
        val clickBtn = findViewById<Button>(R.id.clickBtn)
        val count = findViewById<TextView>(R.id.count)
        val timeCheck = findViewById<TextView>(R.id.timeCheck)
        val waitText = findViewById<TextView>(R.id.waitText)
        val clickText = findViewById<TextView>(R.id.clickText)


        started=false

        gameLayout.setBackgroundColor(Color.parseColor("#c0102a"))
        clickBtn.text = getString(R.string.Ready)
        count.text = getString(R.string.count,i)
        timeCheck.text = getString(R.string.timeCheck,time)
        timeCheck.visibility = View.VISIBLE
        waitText.visibility = View.VISIBLE
        clickText.visibility = View.INVISIBLE
        totalTime+=time
        time=0
    }

    override fun onBackPressed() {

        if (System.currentTimeMillis() - backPressedTime < 2000) {
            finish()
            return
        }

        backPressedTime = System.currentTimeMillis()
    }

}
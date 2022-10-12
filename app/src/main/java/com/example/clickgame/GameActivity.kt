package com.example.clickgame

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.concurrent.thread

class GameActivity : AppCompatActivity() {
    var time = 0
    var totalTime = 0
    var started = false

    val TAG = "GameActivity"
    private var backPressedTime : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val clickBtn = findViewById<Button>(R.id.clickBtn)

        val num = (500..5000).random()

        var i = 1

        Handler(Looper.getMainLooper()).postDelayed({

            start()

        }, num.toLong())

        clickBtn.setOnClickListener {

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

            val num = (500..3500).random()
            Log.d(TAG,num.toString())

            Handler(Looper.getMainLooper()).postDelayed({

                start()

            }, num.toLong())
        }




    }

    fun start(){

        val gameLayout = findViewById<ConstraintLayout>(R.id.gameLayout)
        val clickBtn = findViewById<Button>(R.id.clickBtn)
        val timeCheck = findViewById<TextView>(R.id.timeCheck)
        val waitText = findViewById<TextView>(R.id.waitText)
        val clickText = findViewById<TextView>(R.id.clickText)

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
        clickBtn.text = "Click"
    }

    fun stop(i:Int){
        val gameLayout = findViewById<ConstraintLayout>(R.id.gameLayout)
        val clickBtn = findViewById<Button>(R.id.clickBtn)
        val count = findViewById<TextView>(R.id.count)
        val timeCheck = findViewById<TextView>(R.id.timeCheck)
        val waitText = findViewById<TextView>(R.id.waitText)
        val clickText = findViewById<TextView>(R.id.clickText)


        started=false

        gameLayout.setBackgroundColor(Color.parseColor("#c0102a"))
        clickBtn.text = "Ready"
        count.text = "$i/5"
        timeCheck.text = "${time}ms"
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
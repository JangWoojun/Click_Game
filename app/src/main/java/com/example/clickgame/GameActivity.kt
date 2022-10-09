package com.example.clickgame

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val gameLayout = findViewById<ConstraintLayout>(R.id.gameLayout)
        val clickBtn = findViewById<Button>(R.id.clickBtn)
        val count = findViewById<TextView>(R.id.count)
        val timeCheck = findViewById<TextView>(R.id.timeCheck)

        val num = (500..5000).random()

        var i = 1

        Handler(Looper.getMainLooper()).postDelayed({

            start()

        }, num.toLong())

        clickBtn.setOnClickListener {

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

        started = true
        thread(start=true) {

            while (true){
                if (!started) break

                Thread.sleep(1)
                time+=1
            }

        }
        gameLayout.setBackgroundColor(Color.parseColor("#90ee90"))
        timeCheck.setTextColor(Color.parseColor("#90ee90"))
        clickBtn.text = "Click"
    }

    fun stop(i:Int){
        val gameLayout = findViewById<ConstraintLayout>(R.id.gameLayout)
        val clickBtn = findViewById<Button>(R.id.clickBtn)
        val count = findViewById<TextView>(R.id.count)
        val timeCheck = findViewById<TextView>(R.id.timeCheck)

        started=false

        gameLayout.setBackgroundColor(Color.parseColor("#FFEA7D"))
        clickBtn.text = "Ready"
        count.text = "$i/5"
        timeCheck.text = "${time}ms"
        timeCheck.setTextColor(Color.parseColor("#ffffff"))
        totalTime+=time
        time=0
    }

}
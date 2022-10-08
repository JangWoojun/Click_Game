package com.example.clickgame

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.concurrent.thread

class GameActivity : AppCompatActivity() {
    var total = 0
    var started = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val gameLayout = findViewById<ConstraintLayout>(R.id.gameLayout)
        val clickBtn = findViewById<Button>(R.id.clickBtn)
        val count = findViewById<TextView>(R.id.count)

    for (i in 1..5){
        val num = randomNum()
        Handler(Looper.getMainLooper()).postDelayed({
            start()
            gameLayout.setBackgroundColor(Color.parseColor("#90ee90"))
            clickBtn.text = "Click"

            clickBtn.setOnClickListener {
                stop()
                count.text = "$i/5"
            }
        }, num.toLong())

    }



    }
    fun randomNum(): Int {
        return (1000..4000).random()
    }
    fun start(){
        thread(start=true) {

            while (true){
                if (!started) break

                Thread.sleep(1)
                total++
            }
        }

    }
    fun stop(){
        started=false
        val gameLayout = findViewById<ConstraintLayout>(R.id.gameLayout)
        val clickBtn = findViewById<Button>(R.id.clickBtn)
        gameLayout.setBackgroundColor(Color.parseColor("#FFEA7D"))
        clickBtn.text = "Ready"
    }
}
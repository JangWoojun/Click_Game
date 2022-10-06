package com.example.clickgame

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
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

        TODO("초록색으로 바뀌고 버튼 클릭하면 시간 뜨게 하고 다시 새 게임 준비")

    for (i in 0..5){
        val num = randomNum()
        Handler(Looper.getMainLooper()).postDelayed({
            start()
            gameLayout.setBackgroundColor(Color.parseColor("#90ee90"))
            clickBtn.text = "Click"

            clickBtn.setOnClickListener {
                stop()
                Toast.makeText(this,total.toString(),Toast.LENGTH_SHORT).show()
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
    }
}
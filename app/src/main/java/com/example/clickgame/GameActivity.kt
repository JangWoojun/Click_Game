package com.example.clickgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import kotlin.concurrent.thread
import java.util.Random

class GameActivity : AppCompatActivity() {
    var total = 0
    var started = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

    for (i in 0..5){
        val num = random()
        Handler(Looper.getMainLooper()).postDelayed({
            start()
        }, num.toLong())

    }

TODO("랜덤값으로 나온 시간이후 스탑하여 나온 시간 기록 만들기")

    }
    fun random(): Int {
        val random = Random()
        return random.nextInt(1000 - 6000)
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
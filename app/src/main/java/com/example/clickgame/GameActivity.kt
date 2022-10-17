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

    private var i = 1

    private val handler = Handler(Looper.getMainLooper())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val clickBtn = findViewById<Button>(R.id.clickBtn)
        val gameLayout = findViewById<ConstraintLayout>(R.id.gameLayout)
        val count = findViewById<TextView>(R.id.count)
        val resetBtn = findViewById<Button>(R.id.resetBtn)
        val soon1 = findViewById<TextView>(R.id.soon1)
        val soon2 = findViewById<TextView>(R.id.soon2)
        val timeCheck = findViewById<TextView>(R.id.timeCheck)
        val waitText = findViewById<TextView>(R.id.waitText)
        val clickText = findViewById<TextView>(R.id.clickText)

        num = (2000..4000).random()

        handler.postDelayed({

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
                    overridePendingTransition(0, 0)
                }

                stop(i)
                i++

                chk =false

                val num = (3000..5000).random()

                handler.postDelayed({

                    start()

                }, num.toLong())
            }
            else {
                resetBtn.visibility = View.VISIBLE
                soon1.visibility = View.VISIBLE
                soon2.visibility = View.VISIBLE
                timeCheck.visibility = View.INVISIBLE
                waitText.visibility = View.INVISIBLE
                clickText.visibility = View.INVISIBLE
                clickBtn.visibility = View.INVISIBLE

                handler.removeCallbacksAndMessages(null);
                time = 0
                totalTime = 0
                i = 0
                gameLayout.setBackgroundColor(Color.parseColor("#a9cbd7"))
                count.text = getString(R.string.count,i)

                resetBtn.setOnClickListener {
                    reset()
                    i++
                }
            }
        }

    }

    private fun start(){

        val gameLayout = findViewById<ConstraintLayout>(R.id.gameLayout)
        val clickBtn = findViewById<Button>(R.id.clickBtn)
        val timeCheck = findViewById<TextView>(R.id.timeCheck)
        val waitText = findViewById<TextView>(R.id.waitText)
        val clickText = findViewById<TextView>(R.id.clickText)
        val resetBtn = findViewById<Button>(R.id.resetBtn)
        val soon1 = findViewById<TextView>(R.id.soon1)
        val soon2 = findViewById<TextView>(R.id.soon2)

        soon1.visibility = View.INVISIBLE
        soon2.visibility = View.INVISIBLE
        resetBtn.visibility = View.INVISIBLE
        gameLayout.setBackgroundColor(Color.parseColor("#2dd12d"))
        timeCheck.visibility = View.INVISIBLE
        waitText.visibility = View.INVISIBLE
        clickText.visibility = View.VISIBLE
        clickBtn.text = getString(R.string.Click)
        clickBtn.visibility = View.VISIBLE

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
        waitText.text = getString(R.string.game_guidance)
        totalTime+=time
        time=0
    }
    private fun reset(){
        val gameLayout = findViewById<ConstraintLayout>(R.id.gameLayout)
        val clickBtn = findViewById<Button>(R.id.clickBtn)
        val waitText = findViewById<TextView>(R.id.waitText)
        val resetBtn = findViewById<Button>(R.id.resetBtn)
        val soon1 = findViewById<TextView>(R.id.soon1)
        val soon2 = findViewById<TextView>(R.id.soon2)

        resetBtn.visibility = View.INVISIBLE
        soon1.visibility = View.INVISIBLE
        soon2.visibility = View.INVISIBLE
        clickBtn.visibility = View.VISIBLE
        waitText.visibility = View.VISIBLE

        gameLayout.setBackgroundColor(Color.parseColor("#c0102a"))
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
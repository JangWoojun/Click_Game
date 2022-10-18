package com.example.clickgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.example.clickgame.databinding.ActivityMultiBinding
import kotlin.concurrent.thread

class MultiActivity : AppCompatActivity() {
    private var backPressedTime : Long = 0
    private lateinit var binding: ActivityMultiBinding

    private val handler1 = Handler(Looper.getMainLooper())
    private val handler2 = Handler(Looper.getMainLooper())

    private var num1 = 0
    private var num2 = 0

    private var time1 = 0
    private var totalTime1 = 0
    private var started1 = false
    private var chk1 = false
    private var time2 = 0
    private var totalTime2 = 0
    private var started2 = false
    private var chk2 = false

    private var i1 = 0
    private var i2 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi)
        binding = ActivityMultiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        num1 = (2000..4000).random()
        handler1.postDelayed({

            start1()

        }, num1.toLong())

        num2 = (2000..4000).random()
        handler2.postDelayed({

            start2()

        }, num2.toLong())




        binding.clickBtn1.setOnClickListener {

            if (chk1) {
                i1++
                stop1(i1)
                chk1 = false
                num1 = (3000..5000).random()
                handler1.postDelayed({

                    start1()

                }, num1.toLong())

            }
            else {
                binding.resetBtn1.visibility = View.VISIBLE
                binding.player1soon1.visibility = View.VISIBLE
                binding.player1soon2.visibility = View.VISIBLE
                binding.timeCheck1.visibility = View.INVISIBLE
                binding.waitText1.visibility = View.INVISIBLE
                binding.clickText1.visibility = View.INVISIBLE
                binding.clickBtn1.visibility = View.INVISIBLE

                handler1.removeCallbacksAndMessages(null)
                time1 = 0
                totalTime1 = 0
                i1 = 0
                binding.gameLayout1.setBackgroundResource(R.drawable.border_line_blue_right)
                binding.count1.text = getString(R.string.count,i1)
                binding.resetBtn1.setOnClickListener {
                    reset1()
                    i1++
                }
            }
            if (i1 == 5) {
                handler1.removeCallbacksAndMessages(null)
                binding.gameLayout1.setBackgroundResource(R.drawable.border_line_blue_right)
                binding.waitText1.text = getString(R.string.wait)
            }
            if (i2 == 5 && i1 == 5) {
                val intent = Intent(this, MultiMaxScoreActivity::class.java)
                totalTime2 /= 5
                totalTime1 /= 5
                intent.putExtra("maxScore1", totalTime1.toString())
                intent.putExtra("maxScore2", totalTime2.toString())
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                overridePendingTransition(0, 0)
            }

        }

        binding.clickBtn2.setOnClickListener {

            if (chk2) {
                i2++
                stop2(i2)
                chk2 = false
                num2 = (3000..5000).random()
                handler2.postDelayed({

                    start2()

                }, num2.toLong())
            }
            else {
                binding.resetBtn2.visibility = View.VISIBLE
                binding.player2soon1.visibility = View.VISIBLE
                binding.player2soon2.visibility = View.VISIBLE
                binding.timeCheck2.visibility = View.INVISIBLE
                binding.waitText2.visibility = View.INVISIBLE
                binding.clickText2.visibility = View.INVISIBLE
                binding.clickBtn2.visibility = View.INVISIBLE

                handler2.removeCallbacksAndMessages(null)
                time2 = 0
                totalTime2 = 0
                i2 = 0
                binding.gameLayout2.setBackgroundResource(R.drawable.border_line_blue_left)
                binding.count2.text = getString(R.string.count,i2)
                binding.resetBtn2.setOnClickListener {
                    reset2()
                    i2++
                }
            }
            if (i2 == 5) {
                handler2.removeCallbacksAndMessages(null)
                binding.gameLayout2.setBackgroundResource(R.drawable.border_line_blue_left)
                binding.waitText2.text = getString(R.string.wait)
            }
            if (i2 == 5 && i1 == 5) {
                val intent = Intent(this, MultiMaxScoreActivity::class.java)
                totalTime2 /= 5
                totalTime1 /= 5
                intent.putExtra("maxScore1", totalTime1.toString())
                intent.putExtra("maxScore2", totalTime2.toString())
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                overridePendingTransition(0, 0)
            }

        }


    }
        private fun start1() {
            binding.player1soon1.visibility = View.INVISIBLE
            binding.player1soon2.visibility = View.INVISIBLE
            binding.resetBtn1.visibility = View.INVISIBLE
            binding.gameLayout1.setBackgroundResource(R.drawable.border_line_green_right)
            binding.timeCheck1.visibility = View.INVISIBLE
            binding.waitText1.visibility = View.INVISIBLE
            binding.clickText1.visibility = View.VISIBLE
            binding.clickBtn1.text = getString(R.string.Click)
            binding.clickBtn1.visibility = View.VISIBLE


            chk1 = true
            started1 = true
            thread(start = true) {
                while (true) {
                    if (!started1) break

                    Thread.sleep(1)
                    time1 += 1
                }
            }
        }

        private fun stop1(i1: Int) {
            started1 = false

            binding.gameLayout1.setBackgroundResource(R.drawable.border_line_red_right)
            binding.clickBtn1.text = getString(R.string.Ready)
            binding.count1.text = getString(R.string.count,i1)
            binding.timeCheck1.text = getString(R.string.timeCheck,time1)
            binding.timeCheck1.visibility = View.VISIBLE
            binding.waitText1.visibility = View.VISIBLE
            binding.clickText1.visibility = View.INVISIBLE
            binding.waitText1.text = getString(R.string.game_guidance)

            totalTime1 += time1
            time1 = 0
        }

        private fun reset1() {
            binding.resetBtn1.visibility = View.INVISIBLE
            binding.player1soon1.visibility = View.INVISIBLE
            binding.player1soon2.visibility = View.INVISIBLE
            binding.clickBtn1.visibility = View.VISIBLE
            binding.waitText1.visibility = View.VISIBLE

            binding.gameLayout1.setBackgroundResource(R.drawable.border_line_red_right)


            num1 = (3000..5000).random()

            handler1.postDelayed({

                start1()

            }, num1.toLong())
        }

        private fun start2() {
            binding.player2soon1.visibility = View.INVISIBLE
            binding.player2soon2.visibility = View.INVISIBLE
            binding.resetBtn2.visibility = View.INVISIBLE
            binding.gameLayout2.setBackgroundResource(R.drawable.border_line_green_left)
            binding.timeCheck2.visibility = View.INVISIBLE
            binding.waitText2.visibility = View.INVISIBLE
            binding.clickText2.visibility = View.VISIBLE
            binding.clickBtn2.text = getString(R.string.Click)
            binding.clickBtn2.visibility = View.VISIBLE

            chk2 = true
            started2 = true
            thread(start = true) {
                while (true) {
                    if (!started2) break

                    Thread.sleep(1)
                    time2 += 1
                }
            }
        }

        private fun stop2(i2: Int) {
            started2 = false

            binding.gameLayout2.setBackgroundResource(R.drawable.border_line_red_left)
            binding.clickBtn2.text = getString(R.string.Ready)
            binding.count2.text = getString(R.string.count,i2)
            binding.timeCheck2.text = getString(R.string.timeCheck,time2)
            binding.timeCheck2.visibility = View.VISIBLE
            binding.waitText2.visibility = View.VISIBLE
            binding.clickText2.visibility = View.INVISIBLE
            binding.waitText2.text = getString(R.string.game_guidance)

            totalTime2 += time2
            time2 = 0
        }

        private fun reset2() {
            binding.resetBtn2.visibility = View.INVISIBLE
            binding.player2soon1.visibility = View.INVISIBLE
            binding.player2soon2.visibility = View.INVISIBLE
            binding.clickBtn2.visibility = View.VISIBLE
            binding.waitText2.visibility = View.VISIBLE

            binding.gameLayout2.setBackgroundResource(R.drawable.border_line_red_left)

            num2 = (3000..5000).random()

            handler2.postDelayed({

                start2()

            }, num2.toLong())
        }
    override fun onBackPressed() {

        if (System.currentTimeMillis() - backPressedTime < 2000) {
            finish()
            return
        }

        backPressedTime = System.currentTimeMillis()
    }

}


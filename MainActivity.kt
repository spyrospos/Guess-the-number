package com.example.guessthenumber

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.guessthenumber.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var answer : Int = 0
    var attempts : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        generateAnswer()

        binding.btnPlayAgain.setOnClickListener {
            restart()
        }

        binding.btnSubmit.setOnClickListener {
            check()
            binding.etUserType.text.clear()
        }

        binding.btnExit.setOnClickListener { finish() }
    }

    fun generateAnswer(){
        answer = Random.nextInt(1,100)
    }

    fun restart(){
        generateAnswer()
        binding.tvHidden.text = "??"
        binding.tvGuess.text = "Guess the number"
        attempts = 0
        binding.tvAttempts.text = ""
    }

    fun check() {
        try {
            val userguess = binding.etUserType.text.toString().toInt()

            if (userguess == answer) {
                attempts += 1
                binding.tvGuess.text = "You won! ${answer} was the number"
                binding.tvHidden.text = answer.toString()
                binding.tvAttempts.text = "Total attempts ${attempts}"
            } else if (userguess > answer) {
                binding.tvGuess.text = "Try something lower!"
                attempts += 1
            } else {
                binding.tvGuess.text = "Try something higher!"
                attempts += 1
            }

        }catch (e: NumberFormatException){
            binding.tvHidden.text = "Invalid choice"
        }
    }
}
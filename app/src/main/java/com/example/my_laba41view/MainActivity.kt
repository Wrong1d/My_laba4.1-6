package com.example.my_laba41view


import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.app.AlertDialog
import com.example.my_laba41view.databinding.ActivityMainBinding
import android.content.Intent
import android.widget.Toast



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val questions = listOf(
        "Canberra is the capital of Australia.",
        "The current president of the United States is Joe Biden.",
        "Australia is an island continent."
    )

    private val correctAnswers = listOf(true, true, true)

    private var currentIndex = 0
    private var score = 0
    private var cheatCount = 0
    private val maxCheats = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Восстанавливаем состояние при повороте экрана
        savedInstanceState?.let {
            currentIndex = it.getInt("currentIndex")
            score = it.getInt("score")
            cheatCount = it.getInt("cheatCount", 0)
        }

        updateQuestion()

        binding.trueButton.setOnClickListener { checkAnswer(true) }
        binding.falseButton.setOnClickListener { checkAnswer(false) }

        binding.nextButton.setOnClickListener {
            currentIndex++
            if (currentIndex < questions.size) {
                updateQuestion()
                enableAnswerButtons()
            } else {
                showFinalScore()
                binding.nextButton.isEnabled = false
                binding.nextButton.visibility = Button.INVISIBLE
            }
        }

        binding.cheatButton.setOnClickListener {
            if (cheatCount < maxCheats) {
                val intent = Intent(this, CheatActivity::class.java).apply {
                    putExtra("answer", correctAnswers[currentIndex])
                }
                startActivity(intent)
                cheatCount++
            } else {
                Toast.makeText(this, "Подсказки закончились!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("currentIndex", currentIndex)
        outState.putInt("score", score)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = correctAnswers[currentIndex]
        if (userAnswer == correctAnswer) {
            score++
        }

        disableAnswerButtons()
    }

    private fun updateQuestion() {
        binding.questionTextView.text = questions[currentIndex]
    }

    private fun disableAnswerButtons() {
        binding.trueButton.isEnabled = false
        binding.falseButton.isEnabled = false
    }

    private fun enableAnswerButtons() {
        binding.trueButton.isEnabled = true
        binding.falseButton.isEnabled = true
    }

    private fun showFinalScore() {
        val message = "Вы правильно ответили на $score из ${questions.size} вопросов."
        AlertDialog.Builder(this)
            .setTitle("Результат")
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()

    }

}
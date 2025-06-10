package com.example.my_laba41view


import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
im

import android.os.Bundle
import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    private val questions = listOf(
        "Canberra is the capital of Australia.",
        "The current president of the United States is Joe Biden.",
        "Australia is an island continent."
    )

    private val correctAnswers = listOf(true, true, true)

    private var currentIndex = 0
    private var score = 0

    private lateinit var questionTextView: TextView
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        questionTextView = findViewById(R.id.question_text_view)
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)

        updateQuestion()

        trueButton.setOnClickListener { checkAnswer(true) }
        falseButton.setOnClickListener { checkAnswer(false) }
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = correctAnswers[currentIndex]
        if (userAnswer == correctAnswer) {
            score++
        }

        disableAnswerButtons()
    }

    private fun updateQuestion() {
        questionTextView.text = questions[currentIndex]
    }

    private fun disableAnswerButtons() {
        trueButton.isEnabled = false
        falseButton.isEnabled = false
    }
}
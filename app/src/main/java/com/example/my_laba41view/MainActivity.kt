package com.example.my_laba41view


import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
im

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val questions = listOf(
        "Canberra is the capital of Australia.",
        "The current president of the United States is Joe Biden.",
        "Australia is an island continent."
    )

    private var currentIndex = 0

    private lateinit var questionTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        questionTextView = findViewById(R.id.question_text_view)

        updateQuestion()
    }

    private fun updateQuestion() {
        questionTextView.text = questions[currentIndex]
    }
}
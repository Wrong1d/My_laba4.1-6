package com.example.my_laba41view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.my_laba41view.databinding.ActivityCheatBinding


class CheatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Получаем правильный ответ из Intent
        val answerIsTrue = intent.getBooleanExtra("answer", false)

        // Показываем ответ
        binding.answerTextView.text = if (answerIsTrue) "Правильный ответ: Да" else "Правильный ответ: Нет"

        // Показываем версию API
        binding.apiLevelTextView.text = "API Level: ${android.os.Build.VERSION.SDK_INT}"
    }
}
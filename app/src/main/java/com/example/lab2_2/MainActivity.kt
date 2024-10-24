package com.example.lab2_2

import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var editText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        editText = findViewById(R.id.editTextText)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val numberFilter = InputFilter { source, start, end, dest, dstart, dend ->
            for (i in start until end) {
                val character = source[i]
                // Разрешаем только числа от 2 до 9
                if (character !in '2'..'9') {
                    return@InputFilter ""
                }
            }
            null // Возвращаем null, чтобы разрешить ввод, если все символы допустимы
        }
        // Устанавливаем фильтр на длину в 1 символ
        val lengthFilter = InputFilter.LengthFilter(1)

        // Применяем оба фильтра к EditText
        editText.filters = arrayOf(numberFilter, lengthFilter)
    }
    fun openAllNumberActivity(view: View) {
        // Создаем Intent для перехода на SecondActivity
        val intent = Intent(this, AllNumberActivity::class.java)
        // Запускаем SecondActivity
        startActivity(intent)
    }
    fun openOneNumberActivity(view: View) {
        if (editText.getText().toString().isEmpty()) return
        val intent = Intent(this, OneNumberActivity::class.java)
        intent.putExtra("inputNumber", editText.getText().toString())
        startActivity(intent)
    }
}
package com.example.lab2_2

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class AllNumberActivity : AppCompatActivity() {
    lateinit var textView1: TextView
    lateinit var textView2: TextView
    lateinit var editText1: EditText
    lateinit var listNumber: MutableList<MutableList<Int>>
    lateinit var answers: MutableList<Int>
    var currentQuestion = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_all_number)
        listNumber = mutableListOf()
        answers = mutableListOf()
        textView1 = findViewById(R.id.textView1)
        textView2 = findViewById(R.id.textView2)
        editText1 = findViewById(R.id.editText1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        generateQuestion()
    }
    fun onButtonClick(view: View) {
        if (editText1.getText().isEmpty())
            return
        if ((listNumber[currentQuestion-1][0]*listNumber[currentQuestion-1][1] == editText1.getText().toString().toInt())) {
            answers.add(1)
            Toast.makeText(this, "Правильный ответ", Toast.LENGTH_SHORT).show()
            }
        else {answers.add(0)
            Toast.makeText(this, "Неправильный ответ", Toast.LENGTH_SHORT).show()
        }
        editText1.setText("")
        if (currentQuestion == 20) {
            Toast.makeText(this, "Процент правильных ответов "+ (answers.sum() / 20.0 * 100).toString(), Toast.LENGTH_SHORT)
                .show()
            finish()
        }
        generateQuestion()
    }
    fun generateQuestion() {
        listNumber.add(mutableListOf(Random.nextInt(2,10), Random.nextInt(2, 10)))
        currentQuestion += 1
        textView1.setText("Вопрос номер " + currentQuestion.toString())
        textView2.setText(listNumber[currentQuestion-1][0].toString() + " x " + listNumber[currentQuestion-1][1].toString())
    }
}
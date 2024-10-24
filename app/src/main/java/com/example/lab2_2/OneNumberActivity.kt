package com.example.lab2_2

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class OneNumberActivity : AppCompatActivity() {
    lateinit var textView: TextView
    lateinit var editText2: EditText
    lateinit var textView1: TextView
    lateinit var textView2: TextView
    lateinit var listNumber: MutableList<Int>
    lateinit var answers: MutableList<Int>
    var currentQuestion = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_one_number)
        listNumber = mutableListOf()
        answers = mutableListOf()
        editText2 = findViewById(R.id.editText2)
        textView1 = findViewById(R.id.textView1)
        textView2 = findViewById(R.id.textView2)
        textView = findViewById(R.id.textView)
        textView.setText(intent.getStringExtra("inputNumber").toString())
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        generateQuestion()
    }
    fun generateQuestion() {
        listNumber.add(Random.nextInt(2, 10))
        currentQuestion += 1
        textView1.setText("Вопрос номер " + currentQuestion.toString())
        textView2.setText(listNumber[currentQuestion-1].toString())
    }
    fun onButtonClick(view: View) {
        val inputNumber = intent.getStringExtra("inputNumber")
        if (editText2.getText().isEmpty())
            return
        if ((listNumber[currentQuestion - 1] *  inputNumber.toString().toInt() == editText2.getText()
                .toString().toInt())
        ) {
            answers.add(1)
            Toast.makeText(this, "Правильный ответ", Toast.LENGTH_SHORT).show()
        } else {
            answers.add(0)
            Toast.makeText(this, "Неправильный ответ", Toast.LENGTH_SHORT).show()
        }
        editText2.setText("")
        if (currentQuestion == 20) {
            Toast.makeText(
                this,
                "Процент правильных ответов " + (answers.sum() / 20.0 * 100).toString(),
                Toast.LENGTH_SHORT
            )
                .show()
            finish()
        }
        generateQuestion()
    }
}

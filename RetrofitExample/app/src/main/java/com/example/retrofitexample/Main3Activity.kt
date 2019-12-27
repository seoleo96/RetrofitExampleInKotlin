package com.example.retrofitexample

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class Main3Activity : AppCompatActivity() {

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        val editText = findViewById<EditText>(R.id.editText)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this@Main3Activity, MainActivity::class.java)
            val nameOfEditText = editText.text.toString()
            intent.putExtra("key", nameOfEditText)
            startActivity(intent)
        }
    }
}

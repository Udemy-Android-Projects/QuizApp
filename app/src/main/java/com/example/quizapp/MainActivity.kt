package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnStart : Button = findViewById(R.id.btnStart)
        val etName : EditText = findViewById(R.id.etName)
        btnStart.setOnClickListener{
            if(etName.text.isEmpty()){
                Toast.makeText(this,"Please enter name",Toast.LENGTH_LONG).show()
            }else{
                // Initialized by passing the package context and receiving app component
                val intent = Intent(this,QuizQuestionActivity::class.java)
                //The putExtra uses a key value identification marker.[putExtra(key,value)]
                intent.putExtra(Constants.USER_NAME,etName.text.toString())
                startActivity(intent)
                //Close previous activity
                finish()
            }
        }
    }
}
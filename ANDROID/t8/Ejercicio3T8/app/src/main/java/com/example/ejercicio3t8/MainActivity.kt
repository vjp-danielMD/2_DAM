package com.example.ejercicio3t8

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btnComenzar = findViewById<Button>(R.id.button)
        val btnSalir = findViewById<Button>(R.id.button2)

        btnComenzar.setOnClickListener {
            val intent = Intent(this, Pregunta1::class.java)
            startActivity(intent)
        }

        btnSalir.setOnClickListener {
            finish()
        }
    }
}
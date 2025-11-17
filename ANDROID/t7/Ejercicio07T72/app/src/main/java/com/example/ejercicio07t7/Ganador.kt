package com.example.ejercicio07t7

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Ganador : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ganador)
        val textViewGanador: TextView = findViewById(R.id.TextViewGanadorNombre)

        val ganadorNombre = intent.getStringExtra("GANADOR")

        textViewGanador.text = ganadorNombre
    }
}
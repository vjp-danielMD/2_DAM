package com.example.ejercicio03t7

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Saludo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_saludo)

        val nombre: String? = intent.getStringExtra("nombre")
        val saludo: TextView = findViewById<TextView>(R.id.textView)
        saludo.text = getString(R.string.saludo, nombre)
    }
}
package com.example.ejercicio03t7

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val nombreEditText: EditText = findViewById<EditText>(R.id.nombreTextField)
        val saludoButton: Button = findViewById<Button>(R.id.buttonSaludo)

        saludoButton.setOnClickListener {
            val nombre: String = nombreEditText.text.toString()

            if (nombre.isBlank()){ Toast.makeText(
                this,
                R.string.aviso,
                Toast.LENGTH_SHORT
                    ).show()
            } else {
                val intent: Intent = Intent(this, Saludo::class.java)
                intent.putExtra("nombre", nombre)
                startActivity(intent)
            }

        }
    }
}
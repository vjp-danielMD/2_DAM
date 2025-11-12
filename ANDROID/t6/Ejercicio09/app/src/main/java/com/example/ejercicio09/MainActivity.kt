package com.example.ejercicio09

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        var contador: Int = 0;

        val supButton: Button = findViewById<Button>(R.id.SupButton)
        val medButton: Button = findViewById<Button>(R.id.MedButton)
        val infButton: Button = findViewById<Button>(R.id.InfButton)
        val toastButton: Button = findViewById<Button>(R.id.ToastButton)

        val ultimoText: TextView = findViewById<TextView>(R.id.ultimoTextView)
        val contadorText: TextView = findViewById<TextView>(R.id.contadorTextView)

        supButton.setOnClickListener {
            ultimoText.setText(R.string.superior)
            contador++;
            contadorText.setText(contador.toString())
        }

        medButton.setOnClickListener {
            ultimoText.setText(R.string.medio)
            contador++;
            contadorText.setText(contador.toString())
        }

        infButton.setOnClickListener {
            ultimoText.setText(R.string.inferior)
            contador++;
            contadorText.setText(contador.toString())
        }

        toastButton.setOnClickListener {
            Toast.makeText(this, "¡Botón pulsado!", Toast.LENGTH_SHORT).show()
        }
    }
}
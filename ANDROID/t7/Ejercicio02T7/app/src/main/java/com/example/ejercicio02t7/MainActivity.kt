package com.example.ejercicio02t7

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val notaMates: EditText = findViewById(R.id.editTextNumber1)
        val notaLengua: EditText = findViewById(R.id.editTextNumber2)
        val notaHistoria: EditText = findViewById(R.id.editTextNumber3)
        val botonMedia: Button = findViewById(R.id.botonMedia)

        botonMedia.setOnClickListener {
            val matesStr = notaMates.text.toString()
            val lenguaStr = notaLengua.text.toString()
            val historiaStr = notaHistoria.text.toString()

            if (matesStr.isBlank() || lenguaStr.isBlank() || historiaStr.isBlank()) {
                Toast.makeText(this, "Rellena todas las notas", Toast.LENGTH_SHORT).show()
            } else {
                val mates = matesStr.toIntOrNull()
                val lengua = lenguaStr.toIntOrNull()
                val historia = historiaStr.toIntOrNull()

                if (mates == null || lengua == null || historia == null) {
                    Toast.makeText(this, "Introduce solo números válidos", Toast.LENGTH_SHORT).show()
                } else if (mates < 0 || mates > 10 ||
                    lengua < 0 || lengua > 10 ||
                    historia < 0 || historia > 10) {
                    Toast.makeText(this, "Las notas deben estar entre 0 y 10", Toast.LENGTH_SHORT).show()
                } else {
                    val media = (mates + lengua + historia) / 3.0
                    val intent = Intent(this, Media::class.java)
                    intent.putExtra("media", media)
                    startActivity(intent)
                }
            }
        }
    }
}

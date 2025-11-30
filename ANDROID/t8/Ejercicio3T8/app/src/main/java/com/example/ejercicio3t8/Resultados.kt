package com.example.ejercicio3t8

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Resultados : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultados)

        val textViewCorrectas = findViewById<TextView>(R.id.textViewCorrectas)
        val textViewErroneas = findViewById<TextView>(R.id.textViewErroneas)
        val textViewSinContestar = findViewById<TextView>(R.id.textViewSinContestar)
        val textViewNota = findViewById<TextView>(R.id.textViewNOTA)
        val btnVolverInicio = findViewById<Button>(R.id.button2)

        // Recuperar datos del Intent
        val puntuacion = intent.getFloatExtra("puntuacion", 0f)
        val correctas = intent.getIntExtra("correctas", 0)
        val erroneas = intent.getIntExtra("erroneas", 0)
        val sinContestar = intent.getIntExtra("sinContestar", 0)

        // Mostrar resultados
        textViewCorrectas.text = correctas.toString()
        textViewErroneas.text = erroneas.toString()
        textViewSinContestar.text = sinContestar.toString()
        textViewNota.text = puntuacion.toString()

        btnVolverInicio.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
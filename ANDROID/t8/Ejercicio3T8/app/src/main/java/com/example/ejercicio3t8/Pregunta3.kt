package com.example.ejercicio3t8

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class Pregunta3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pregunta3)

        val spinner = findViewById<Spinner>(R.id.spinner)
        val btnSiguiente = findViewById<Button>(R.id.button2)

        // Cargar opciones desde recursos
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.hardware,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        btnSiguiente.setOnClickListener {
            val respuestaSeleccionada = spinner.selectedItem.toString()
            val puntuacion = calcularPuntuacion(respuestaSeleccionada, "Memoria RAM")

            // Recuperar y acumular puntuación
            val puntuacionTotal = intent.getFloatExtra("puntuacion", 0f) + puntuacion
            val correctas = intent.getIntExtra("correctas", 0) + if (puntuacion == 2.5f) 1 else 0
            val erroneas = intent.getIntExtra("erroneas", 0) + if (puntuacion == -1f) 1 else 0
            val sinContestar = intent.getIntExtra("sinContestar", 0) + if (puntuacion == 0f) 1 else 0

            val intent = Intent(this, Pregunta4::class.java)
            intent.putExtra("puntuacion", puntuacionTotal)
            intent.putExtra("correctas", correctas)
            intent.putExtra("erroneas", erroneas)
            intent.putExtra("sinContestar", sinContestar)
            startActivity(intent)
            finish()
        }
    }

    private fun calcularPuntuacion(respuesta: String, respuestaCorrecta: String): Float {
        return when (respuesta) {
            respuestaCorrecta -> 2.5f
            else -> -1f
        }
    }
}
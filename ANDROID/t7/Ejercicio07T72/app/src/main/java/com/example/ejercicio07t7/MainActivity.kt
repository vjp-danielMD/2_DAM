package com.example.ejercicio07t7

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private val listaNombres = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val editTextNombre: EditText = findViewById(R.id.editTextNombre)
        val btnBorrar: Button = findViewById(R.id.btnBorrar)
        val btnInsertar: Button = findViewById(R.id.btnInsertar)
        val btnSortear: Button = findViewById(R.id.buttonSortear)

        // Botón BORRAR
        btnBorrar.setOnClickListener {
            editTextNombre.text.clear()
        }

        // Botón INSERTAR
        btnInsertar.setOnClickListener {
            val nombre = editTextNombre.text.toString()
            if (nombre.isEmpty()) {
                Toast.makeText(this, R.string.empty_name, Toast.LENGTH_SHORT).show()
            } else {
                listaNombres.add(nombre)
                editTextNombre.text.clear()
                Toast.makeText(this, R.string.new_name, Toast.LENGTH_SHORT).show()
            }
        }

        // Botón SORTEAR
        btnSortear.setOnClickListener {
            if (listaNombres.isEmpty()) {
                Toast.makeText(this, "No hay socios para sortear", Toast.LENGTH_SHORT).show()
            } else {
                val aleatorio: Int = Random.nextInt(0, listaNombres.size)
                val socioGanador = listaNombres[aleatorio]

                val intent = Intent(this, Ganador::class.java)
                intent.putExtra("GANADOR", socioGanador)
                startActivity(intent)
            }
        }
    }
}
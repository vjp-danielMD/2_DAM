package com.example.t10ej1

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referenciar las vistas mediante findViewById
        val etNombre = findViewById<EditText>(R.id.et_nombre)
        val etDatos = findViewById<EditText>(R.id.et_datos)
        val btnGrabar = findViewById<Button>(R.id.btn_grabar)
        val btnBuscar = findViewById<Button>(R.id.btn_buscar)

        // Configurar el botón GRABAR
        btnGrabar.setOnClickListener {
            val nombre = etNombre.text.toString()
            val datos = etDatos.text.toString()

            val preferencias = getSharedPreferences("agenda", Context.MODE_PRIVATE)
            val editor = preferencias.edit()
            editor.putString(nombre, datos)
            editor.apply() // También se puede usar commit()

            Toast.makeText(this, "Datos guardados", Toast.LENGTH_SHORT).show()
        }

        // Configurar el botón BUSCAR
        btnBuscar.setOnClickListener {
            val nombre = etNombre.text.toString()
            val preferencias = getSharedPreferences("agenda", Context.MODE_PRIVATE)
            val datos = preferencias.getString(nombre, "")

            if (datos.isNullOrEmpty()) {
                // Si no se encuentra el contacto o el campo estaba vacío
                etDatos.setText("")
                Toast.makeText(this, "No existe ese contacto", Toast.LENGTH_SHORT).show()
            } else {
                // Si se encuentra, se cargan los datos en el EditText
                etDatos.setText(datos)
            }
        }
    }
}

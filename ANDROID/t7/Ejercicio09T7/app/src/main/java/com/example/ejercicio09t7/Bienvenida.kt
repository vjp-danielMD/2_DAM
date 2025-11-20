package com.example.ejercicio09t7

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Bienvenida : AppCompatActivity() {
    private lateinit var usuario: Usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bienvenida)

        //recuperamos parametros del main
        val nombre = intent.getStringExtra("usuario").toString()
        val rol = intent.getStringExtra("rol").toString()
        usuario = Usuario(nombre, "", rol.toString())

        val textViewSaludo = findViewById<TextView>(R.id.textViewSaludo)
        val btnAdmin = findViewById<Button>(R.id.buttonAdmin)
        val btnToast = findViewById<Button>(R.id.buttonToast)
        val btnAbout = findViewById<Button>(R.id.buttonAbout)

        textViewSaludo.text = getString(R.string.saludo, nombre)

        if (usuario.getRol() == "admin") {
            btnAdmin.visibility = View.VISIBLE
            btnToast.visibility = View.VISIBLE
            btnAbout.visibility = View.VISIBLE
        } else {
            btnAdmin.visibility = View.GONE
            btnToast.visibility = View.GONE
            btnAbout.visibility = View.VISIBLE
        }
        btnAdmin.setOnClickListener {
            textViewSaludo.text = getString(R.string.admin_texto, nombre)
        }

        btnAbout.setOnClickListener {
            textViewSaludo.text = getString(R.string.acerca_de_autor)
        }

        btnToast.setOnClickListener {
            Toast.makeText(this, getString(R.string.sesion_activa, nombre), Toast.LENGTH_SHORT).show()
        }
    }
}
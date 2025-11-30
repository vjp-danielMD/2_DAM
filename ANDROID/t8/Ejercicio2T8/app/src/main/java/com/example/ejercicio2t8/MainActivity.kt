package com.example.ejercicio2t8

import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinner = findViewById< Spinner>(R.id.spinner)

        val adaptador = ArrayAdapter.createFromResource(this, R.array.ciclos, android.R.layout.simple_spinner_item)
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adaptador
        spinner.onItemSelectedListener = this
    }

    override fun onItemSelected(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long
    ) {
        if (position == 1) {
            Toast.makeText(this, getString(R.string.has_elegido) + getString(R.string.smr), Toast.LENGTH_SHORT).show()
        } else if (position == 2) {
            Toast.makeText(this, getString(R.string.has_elegido) + getString(R.string.asir), Toast.LENGTH_SHORT).show()
        } else if (position == 3) {
            Toast.makeText(this, getString(R.string.has_elegido) + getString(R.string.dam), Toast.LENGTH_SHORT).show()
        } else if (position == 4) {
            Toast.makeText(this, getString(R.string.has_elegido) + getString(R.string.daw), Toast.LENGTH_SHORT).show()
        } else if (position == 5) {
            Toast.makeText(this, getString(R.string.has_elegido) + getString(R.string.ceceti), Toast.LENGTH_SHORT).show()
        } else if (position == 6) {
            Toast.makeText(this, getString(R.string.has_elegido) + getString(R.string.ce_videojuegos), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }



}
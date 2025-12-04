package com.example.ejercicio6t8

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_principal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var opcion: TextView = findViewById<TextView>(R.id.opcion)
        when(item.itemId){
            R.id.opcion1_1 -> {
                opcion.text = getString(R.string.opcion1_1)
                return true
            }
            R.id.opcion1_2 -> {
                opcion.text = getString(R.string.opcion1_2)
                return true
            }
            R.id.opcion1_3 -> {
                opcion.text = getString(R.string.opcion1_3)
                return true
            }

            // Grupo 2
            R.id.opcion2_1 -> {
                opcion.text = getString(R.string.opcion2_1)
                return true
            }
            R.id.opcion2_2 -> {
                opcion.text = getString(R.string.opcion2_2)
                return true
            }
            R.id.opcion2_3 -> {
                opcion.text = getString(R.string.opcion2_3)
                return true
            }

            // Grupo 3
            R.id.opcion3_1 -> {
                opcion.text = getString(R.string.opcion3_1)
                return true
            }
            R.id.opcion3_2 -> {
                opcion.text = getString(R.string.opcion3_2)
                return true
            }
            R.id.opcion3_3 -> {
                opcion.text = getString(R.string.opcion3_3)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
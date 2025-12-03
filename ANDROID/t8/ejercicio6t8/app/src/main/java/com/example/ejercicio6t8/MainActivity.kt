package com.example.ejercicio6t8

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_principal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            // Grupo 1
            R.id.opcion1_1 -> {
                //
                return true
            }
            R.id.opcion1_2 -> {
                //
                return true
            }
            R.id.opcion1_3 -> {
                //
                return true
            }

            // Grupo 2
            R.id.opcion2_1 -> {
                //
                return true
            }
            R.id.opcion2_2 -> {
                //
                return true
            }
            R.id.opcion2_3 -> {
                //
                return true
            }

            // Grupo 3
            R.id.opcion3_1 -> {
                //
                return true
            }
            R.id.opcion3_2 -> {
                //
                return true
            }
            R.id.opcion3_3 -> {
                //
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
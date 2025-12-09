package com.example.ejercicio1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Historial : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_historial)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerHistorial)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val historial = intent.getStringArrayListExtra("historial") ?: arrayListOf()
        val adapter = HistorialAdapter(historial)
        recyclerView.adapter = adapter
    }
}
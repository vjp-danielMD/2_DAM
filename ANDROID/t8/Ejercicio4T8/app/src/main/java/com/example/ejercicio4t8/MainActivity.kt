package com.example.ejercicio4t8

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ejercicio4t8.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nombres: List<String> = listOf(
            "Hermanos de sangre",
            "Breaking bad",
            "Friends",
            "Patria",
            "El patrón del mal",
            "Los Simpson",
            "Juego de tronos",
            "Vikingos"
        )
        binding.recyclerViewSeries.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewSeries.adapter = NombreAdapter(nombres){ nombre, pos ->
            Toast.makeText(this, "has elegido la opcion: $nombre", Toast.LENGTH_SHORT).show()
        }
    }
}
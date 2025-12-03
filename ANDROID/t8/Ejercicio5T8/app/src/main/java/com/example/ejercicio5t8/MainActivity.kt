package com.example.ejercicio5t8

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ejercicio5t8.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // declara el objeto de ViewBinding para acceder a las vistas
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // onfla el layout y crea la instancia de binding
        setContentView(binding.root) // establece la vista principal de la actividad usando el binding

        val peliculas: List<Pelicula> = listOf(
            Pelicula("Aquaman", R.drawable.aquaman),
            Pelicula("Batman", R.drawable.batman),
            Pelicula("Capitan America", R.drawable.capitanamerica),
            Pelicula("Hulk", R.drawable.hulk),
            Pelicula("Ironman", R.drawable.ironman),
            Pelicula("Lobezno", R.drawable.lobezno),
            Pelicula("Spiderman", R.drawable.spiderman),
            Pelicula("Superman", R.drawable.superman),
            Pelicula("Thor", R.drawable.thor),
            Pelicula("Wonder Woman", R.drawable.wonderwoman)
        )

        // configura el RecyclerView para que muestre los elementos en una lista vertical
        binding.recyclerViewPeliculas.layoutManager = LinearLayoutManager(this)

        // asigna el adaptador al RecyclerView, pasandole la lista de películas
        binding.recyclerViewPeliculas.adapter = PeliculaAdapter(peliculas)
    }
}

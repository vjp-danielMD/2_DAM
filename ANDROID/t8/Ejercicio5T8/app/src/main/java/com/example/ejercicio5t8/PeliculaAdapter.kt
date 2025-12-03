package com.example.ejercicio5t8

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicio5t8.databinding.ItemPeliculaBinding

// este adaptador conecta la lista de películas con el RecyclerView.
class PeliculaAdapter (
    private val lista: List<Pelicula> // La lista de datos que mostrará el adaptador
) : RecyclerView.Adapter<PeliculaAdapter.PeliculaViewHolder>(){

    // la clase ViewHolder representa cada elemento individual de la lista
    inner class PeliculaViewHolder(val binding: ItemPeliculaBinding)
        : RecyclerView.ViewHolder(binding.root) // 'binding.root' es la vista principal del item

    // crea un nuevo ViewHolder cuando el RecyclerView lo necesita.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculaViewHolder {
        // Infla (crea) la vista del item usando el binding.
        val binding = ItemPeliculaBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return PeliculaViewHolder(binding) // Devuelve el ViewHolder recién creado
    }

    //vincula los datos de una película a las vistas de un ViewHolder
    override fun onBindViewHolder(holder: PeliculaViewHolder, position: Int) {
        val peliculaItem: Pelicula = lista[position] // Obtiene la película en la posición actual.
        holder.binding.textViewTitulo.text = peliculaItem.titulo // Asigna el título al TextView.
        holder.binding.imagePelicula.setImageResource(peliculaItem.imagen) // Asigna la imagen al ImageView.
    }

    // devuelve el número total de elementos en la lista
    override fun getItemCount(): Int {
        return lista.size // El tamaño de la lista determina cuántos items hay.
    }
}

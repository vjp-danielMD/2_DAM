package com.iesvjp.galeriadeswann.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iesvjp.galeriadeswann.R
import com.iesvjp.galeriadeswann.modelo.Articulo

// Añadimos "onItemClick" como parámetro para capturar las pulsaciones
class ArticuloAdapter(
    private var listaArticulos: List<Articulo>,
    private val onItemClick: (Articulo) -> Unit
) : RecyclerView.Adapter<ArticuloAdapter.ArticuloViewHolder>() {

    class ArticuloViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNombre: TextView = view.findViewById(R.id.tvItemNombre)
        val tvPrecio: TextView = view.findViewById(R.id.tvItemPrecio)
        val tvUnidades: TextView = view.findViewById(R.id.tvItemUnidades)
        val tvId: TextView = view.findViewById(R.id.tvItemId)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticuloViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_articulo, parent, false)
        return ArticuloViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticuloViewHolder, position: Int) {
        val articulo = listaArticulos[position]
        holder.tvNombre.text = articulo.nombre
        holder.tvPrecio.text = "Precio: ${articulo.precio} €"
        holder.tvUnidades.text = "Stock: ${articulo.unidades}"
        holder.tvId.text = "ID: ${articulo.id}"

        // Al pulsar la tarjeta, disparamos el listener pasándole el artículo actual
        holder.itemView.setOnClickListener { onItemClick(articulo) }
    }

    override fun getItemCount(): Int = listaArticulos.size

    fun actualizarDatos(nuevaLista: List<Articulo>) {
        listaArticulos = nuevaLista
        notifyDataSetChanged()
    }

    // Función auxiliar que necesitaremos para el Swipe to Delete
    fun obtenerArticuloEnPosicion(posicion: Int): Articulo {
        return listaArticulos[posicion]
    }
}
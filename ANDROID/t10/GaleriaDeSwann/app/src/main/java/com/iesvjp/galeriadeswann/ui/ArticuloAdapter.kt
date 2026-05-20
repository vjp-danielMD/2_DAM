package com.iesvjp.galeriadeswann.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iesvjp.galeriadeswann.R
import com.iesvjp.galeriadeswann.modelo.Articulo

class ArticuloAdapter(
    private var listaArticulos: List<Articulo>,
    private val onClick: (Articulo) -> Unit
) : RecyclerView.Adapter<ArticuloAdapter.ArticuloViewHolder>() {

    class ArticuloViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNombre: TextView = view.findViewById(R.id.tvItemNombre)
        val tvId: TextView = view.findViewById(R.id.tvItemId)
        val tvPrecio: TextView = view.findViewById(R.id.tvItemPrecio)
        val tvUnidades: TextView = view.findViewById(R.id.tvItemUnidades)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticuloViewHolder {
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_articulo, parent, false)
        return ArticuloViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ArticuloViewHolder, position: Int) {
        val articulo = listaArticulos[position]

        holder.tvNombre.text = articulo.nombre
        holder.tvId.text = "ID: ${articulo.id}"
        holder.tvPrecio.text = "Precio: ${articulo.precio} €"
        holder.tvUnidades.text = "Stock: ${articulo.unidades}"

        holder.itemView.setOnClickListener {
            onClick(articulo)
        }
    }

    override fun getItemCount(): Int {
        return listaArticulos.size
    }

    fun actualizarDatos(nuevaLista: List<Articulo>) {
        this.listaArticulos = nuevaLista
        notifyDataSetChanged()
    }

    fun obtenerArticuloEnPosicion(posicion: Int): Articulo {
        return listaArticulos[posicion]
    }
}
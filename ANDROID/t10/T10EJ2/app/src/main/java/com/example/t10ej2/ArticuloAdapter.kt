package com.example.t10ej2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.t10ej2.databinding.ItemArticuloBinding

class ArticuloAdapter(private val articulos: List<Articulo>) :
    RecyclerView.Adapter<ArticuloAdapter.ArticuloViewHolder>() {

    class ArticuloViewHolder(val binding: ItemArticuloBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticuloViewHolder {
        val binding = ItemArticuloBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticuloViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticuloViewHolder, position: Int) {
        val articulo = articulos[position]
        holder.binding.tvCodigo.text = "Código: ${articulo.codigo}"
        holder.binding.tvDescripcion.text = articulo.descripcion
        holder.binding.tvStock.text = "Stock: ${articulo.stock}"
        holder.binding.tvPrecio.text = "${String.format("%.2f", articulo.precio)} €"
    }

    override fun getItemCount() = articulos.size
}

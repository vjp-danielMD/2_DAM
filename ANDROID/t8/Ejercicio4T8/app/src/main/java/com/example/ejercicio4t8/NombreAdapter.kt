package com.example.ejercicio4t8

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicio4t8.databinding.ItemNombreBinding

class NombreAdapter(
    private val lista: List<String>,
    private val onItemClick: (String, Int) -> Unit
) : RecyclerView.Adapter<NombreAdapter.NombreViewHolder>() {

    inner class NombreViewHolder(val binding: ItemNombreBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NombreViewHolder {
        val binding = ItemNombreBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return NombreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NombreViewHolder, position: Int) {
        val nombre: String = lista[position]
        holder.binding.textViewNombreSerie.setText(lista[position])

        holder.binding.root.setOnClickListener {
            val pos = holder.bindingAdapterPosition
            if (pos != RecyclerView.NO_POSITION){
                onItemClick(nombre, pos)
            }
        }
    }

    override fun getItemCount(): Int {
        return lista.size
    }
}

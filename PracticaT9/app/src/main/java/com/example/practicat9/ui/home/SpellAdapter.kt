package com.example.practicat9.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practicat9.R
import com.example.practicat9.modelo.Spell


class SpellAdapter(private var spellsList: List<Spell>) :
        RecyclerView.Adapter<SpellAdapter.SpellViewHolder>() {

    /** ViewHolder que contiene las vistas de cada item de hechizo */
    class SpellViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvSpellName)
        val tvLevel: TextView = itemView.findViewById(R.id.tvSpellLevel)
        val tvSchool: TextView = itemView.findViewById(R.id.tvSpellSchool)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpellViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_spell, parent, false)
        return SpellViewHolder(view)
    }

    override fun onBindViewHolder(holder: SpellViewHolder, position: Int) {
        val spell = spellsList[position]
        holder.tvName.text = spell.name
        holder.tvLevel.text = "Nivel: ${spell.level}"
        holder.tvSchool.text = spell.school
    }

    override fun getItemCount(): Int = spellsList.size

    fun updateSpellsList(newList: List<Spell>) {
        spellsList = newList
        notifyDataSetChanged()
    }
}

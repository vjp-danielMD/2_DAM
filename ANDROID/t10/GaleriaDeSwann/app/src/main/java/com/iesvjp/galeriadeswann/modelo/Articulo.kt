package com.iesvjp.galeriadeswann.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_articulos")
data class Articulo(
    // id autogenerado
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val nombre: String,
    val precio: Double,
    val unidades: Int
)
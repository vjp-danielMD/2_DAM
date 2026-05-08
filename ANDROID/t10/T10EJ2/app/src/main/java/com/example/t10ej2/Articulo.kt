package com.example.t10ej2

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articulos")
data class Articulo(
    @PrimaryKey
    val codigo: Int,
    val descripcion: String,
    val stock: Int,
    val precio: Double
)

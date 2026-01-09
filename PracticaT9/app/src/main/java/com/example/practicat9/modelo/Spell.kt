package com.example.practicat9.modelo

import com.google.gson.annotations.SerializedName

/**
 * Clase de datos que representa un hechizo mágico en el grimorio Utiliza anotaciones
 * @SerializedName para el mapeo JSON con GSON
 */
data class Spell(
        @SerializedName("id")
        val id: String? = null, // Nullable para permitir POST (el servidor genera el ID)
        @SerializedName("name") val name: String,
        @SerializedName("level") val level: Int,
        @SerializedName("school") val school: String,
        @SerializedName("description") val description: String
)

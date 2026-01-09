package com.example.practicat9.servicios

import com.example.practicat9.modelo.Spell
import retrofit2.Response
import retrofit2.http.*

interface APIService {

    /** Obtiene todos los hechizos del grimorio GET http://10.0.2.2:3000/spells */
    @GET("spells") suspend fun getAllSpells(): Response<List<Spell>>

    /** Obtiene un hechizo específico por su ID GET http://10.0.2.2:3000/spells/{id} */
    @GET("spells/{id}") suspend fun getSpellById(@Path("id") id: String): Response<Spell>

    /** Añade un nuevo hechizo al grimorio POST http://10.0.2.2:3000/spells */
    @POST("spells") suspend fun addSpell(@Body spell: Spell): Response<Spell>

    /** Elimina un hechizo por su ID DELETE http://10.0.2.2:3000/spells/{id} */
    @DELETE("spells/{id}") suspend fun deleteSpell(@Path("id") id: String): Response<Void>
}

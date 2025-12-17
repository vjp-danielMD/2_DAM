package com.example.ejercicio01t9.servicios

import com.example.ejercicio01t9.modelos.Device
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

// Interfaz que define los endpoints de la API
interface APIService {
    // Obtener todos los objetos
    @GET("objects")
    suspend fun getObjects(): Response<List<Device>>

    // Obtener un objeto específico por ID
    @GET("objects/{id}")
    suspend fun getObjectById(@Path("id") id: String): Response<Device>
}

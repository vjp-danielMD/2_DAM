package com.example.ejercicio01t9.controladores

import android.util.Log
import com.example.ejercicio01t9.modelos.Device
import com.example.ejercicio01t9.servicios.ClienteRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// Controlador que maneja la lógica de negocio entre la vista y el modelo
class DeviceController {

    // Función para obtener un dispositivo por su ID
    suspend fun obtenerDispositivoPorId(id: String): Device? {
        return withContext(Dispatchers.IO) {
            try {
                // Realizar la petición HTTP
                val response = ClienteRetrofit.apiService.getObjectById(id)
                
                // Verificar si la respuesta fue exitosa
                if (response.isSuccessful) {
                    val device = response.body()
                    Log.d("DeviceController", "Dispositivo obtenido: $device")
                    device
                } else {
                    Log.e("DeviceController", "Error: ${response.code()} - ${response.message()}")
                    null
                }
            } catch (e: Exception) {
                Log.e("DeviceController", "Excepción al obtener dispositivo: ${e.message}")
                e.printStackTrace()
                null
            }
        }
    }

    // Función para obtener todos los dispositivos
    suspend fun obtenerTodosLosDispositivos(): List<Device>? {
        return withContext(Dispatchers.IO) {
            try {
                val response = ClienteRetrofit.apiService.getObjects()
                
                if (response.isSuccessful) {
                    val devices = response.body()
                    Log.d("DeviceController", "Dispositivos obtenidos: ${devices?.size}")
                    devices
                } else {
                    Log.e("DeviceController", "Error: ${response.code()} - ${response.message()}")
                    null
                }
            } catch (e: Exception) {
                Log.e("DeviceController", "Excepción al obtener dispositivos: ${e.message}")
                e.printStackTrace()
                null
            }
        }
    }
}

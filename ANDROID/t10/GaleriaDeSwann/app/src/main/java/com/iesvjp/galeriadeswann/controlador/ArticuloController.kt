package com.iesvjp.galeriadeswann.controlador

import android.content.Context
import com.iesvjp.galeriadeswann.modelo.AppDatabase
import com.iesvjp.galeriadeswann.modelo.Articulo

class ArticuloController(context: Context) {

    private val db = AppDatabase.getDatabase(context)

    suspend fun insertar(articulo: Articulo) {
        db.articuloDao().insertar(articulo)
    }

    suspend fun actualizar(articulo: Articulo) {
        db.articuloDao().actualizar(articulo)
    }

    suspend fun eliminarPorId(id: Int) {
        db.articuloDao().eliminarPorId(id)
    }

    suspend fun obtenerTodos(): List<Articulo> {
        return db.articuloDao().obtenerTodos()
    }

    suspend fun obtenerPorId(id: Int): Articulo? {
        return db.articuloDao().obtenerPorId(id)
    }
}

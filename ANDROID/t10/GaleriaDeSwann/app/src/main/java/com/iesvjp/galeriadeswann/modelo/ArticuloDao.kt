package com.iesvjp.galeriadeswann.modelo

import androidx.room.*

@Dao
interface ArticuloDao {
    @Insert
    suspend fun insertar(articulo: Articulo)

    @Update
    suspend fun actualizar(articulo: Articulo)

    @Delete
    suspend fun eliminar(articulo: Articulo)

    @Query("SELECT * FROM tabla_articulos")
    suspend fun obtenerTodos(): List<Articulo>

    @Query("SELECT * FROM tabla_articulos WHERE id = :id")
    suspend fun obtenerPorId(id: Int): Articulo?

    @Query("DELETE FROM tabla_articulos WHERE id = :id")
    suspend fun eliminarPorId(id: Int)
}
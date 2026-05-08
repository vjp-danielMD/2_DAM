package com.example.t10ej2

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ArticuloDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(articulo: Articulo)

    @Delete
    suspend fun borrar(articulo: Articulo)

    @Update
    suspend fun actualizar(articulo: Articulo)

    @Query("SELECT * FROM articulos WHERE codigo = :codigo")
    suspend fun consultarPorCodigo(codigo: Int): Articulo?

    @Query("SELECT * FROM articulos WHERE descripcion LIKE '%' || :descripcion || '%'")
    suspend fun consultarPorDescripcion(descripcion: String): List<Articulo>

    @Query("SELECT * FROM articulos")
    suspend fun getAll(): List<Articulo>
}

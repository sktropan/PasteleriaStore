package com.example.pasteleriastore.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.pasteleriastore.model.Categoria


@Dao
interface CategoriaDao {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertarCaegoria(categoria: Categoria)

    @Update
    suspend fun actualizarCategoria(categoria: Categoria)

    @Delete
    suspend fun eliminarCategoria(categoria: Categoria)

    @Query ("SELECT * FROM categorias")
    suspend fun  obtenerCategorias(): List<Categoria>

    @Query("SELECT * FROM categorias WHERE categoriaId = :id")
    suspend fun  obtenerCategoriaPorId(id: Int): Categoria?
}
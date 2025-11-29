package com.example.pasteleriastore.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categorias")
data class Categoria (

    @PrimaryKey(autoGenerate = true)
    val categoriaId: Int = 0,
    val nombre: String,
    val descripcion: String?= null // esto puede ser opcional
)

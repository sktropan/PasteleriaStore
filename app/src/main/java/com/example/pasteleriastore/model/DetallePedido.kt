package com.example.pasteleriastore.model

import androidx.room.Entity


@Entity("detallePedido", primaryKeys = ["pedidoId","productoId","categoriaId"])
data class DetallePedido (
    val pedidoId: Int,
    val productoId: Int,
    val categoriaId: Int,
    val cantidad:Int,
    val precio: Double
)
package com.example.pasteleriastore.model



data class CarritoCompra (
    val producto: Producto,
    val cantidad: Int
){
    fun getSubtotal(): Double = producto.precio * cantidad
}
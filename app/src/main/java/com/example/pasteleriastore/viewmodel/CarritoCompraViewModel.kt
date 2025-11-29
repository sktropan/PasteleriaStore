package com.example.pasteleriastore.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.pasteleriastore.model.CarritoCompra
import com.example.pasteleriastore.model.Producto


class CarritoCompraViewModel : ViewModel (){

    private  val _carrito = mutableStateListOf<CarritoCompra>()
    val carrito: List<CarritoCompra> get() =  _carrito


    fun agregarProducto (producto: Producto, cantidad : Int = 1) { val index = _carrito.indexOfFirst { it.producto.productoId == producto.productoId }
        if (index != -1) {
            val item = _carrito[index]
            _carrito[index] = item.copy(cantidad = item.cantidad + cantidad)
        } else {
            _carrito.add(CarritoCompra(producto, cantidad))
        }

    }
    fun actualizarCantidad(productoId: Int, nuevaCantidad: Int) {
        val index = _carrito.indexOfFirst { it.producto.productoId == productoId }
        if (index != -1) {
            val item = _carrito[index]
            _carrito[index] = item.copy(cantidad = nuevaCantidad)
        }
    }

    fun eliminarProducto (productoId: Int){
        _carrito.removeAll { it.producto.productoId == productoId }
    }

    fun calcularTotal (): Double{
        return _carrito.sumOf { it.getSubtotal() }
    }
    fun limpiarCarrito(){
        _carrito.clear()
    }

    fun calcularTotalFormateado(): String {
        val total = calcularTotal()
        return "$${"%,.0f".format(total)} CLP"
    }
}
package com.example.pasteleriastore.data



import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.pasteleriastore.model.DetallePedido

@Dao
interface DetallePedidoDao {
    @Insert
    suspend fun  insertar(detalle: DetallePedido )

    @Update
    suspend fun  actualizar(datalle: DetallePedido )

    @Delete
    suspend fun  eliminar(detalle: DetallePedido )

    @Query("SELECT * FROM detallePedido")
    suspend fun  obtenerTodos(): List<DetallePedido>

    @Query("SELECT * FROM detallePedido WHERE pedidoId = :pedidoId")
    suspend fun  obtenerPorPedido(pedidoId: Int): List<DetallePedido>

    @Query("SELECT * FROM detallePedido WHERE categoriaId = :categoriaId")
    suspend fun obtenerPorCategoria(categoriaId: Int): List<DetallePedido>
}
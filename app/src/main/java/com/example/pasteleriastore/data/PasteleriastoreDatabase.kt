package com.example.pasteleriastore.data

import androidx.room.Database
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.pasteleriastore.model.Usuario
import com.example.pasteleriastore.model.Pedido
import com.example.pasteleriastore.model.Producto
import com.example.pasteleriastore.model.DetallePedido
import com.example.pasteleriastore.model.Categoria
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        Usuario::class,
        Pedido::class,
        Producto::class,
        Categoria::class,
        DetallePedido::class
    ], version = 1
)

abstract class PasteleriastoreDatabase : RoomDatabase(){
    abstract fun usuarioDao(): UsuarioDao
    abstract fun pedidoDao(): PedidoDao

    abstract fun productoDao(): ProductoDao
    abstract fun categoriaDao(): CategoriaDao

    abstract fun detallePedidoDao(): DetallePedidoDao

    companion object {

        private var database: PasteleriastoreDatabase? = null

        fun getDatabase(context: Context): PasteleriastoreDatabase {
            return database?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PasteleriastoreDatabase::class.java,
                    "pastel.db"
                )
                    .fallbackToDestructiveMigration()//elimina la base de datos al canbiar la version
                    .addCallback(object : Callback(){
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            CoroutineScope(Dispatchers.IO).launch(){
                                insertarDatosPorDefecto(database!!)
                            }
                        }
                    })
                    .build()
                database = instance
                instance

            }
        }

        private suspend fun insertarDatosPorDefecto(db: PasteleriastoreDatabase) {
            //Insertar a Usuarios
            val usuarioDao = db.usuarioDao()
            val usuarios = listOf(
                Usuario(nombre = "admi", contrasena = "12345", correo = "admi@pastel.cl"),
                Usuario(nombre = "cliente", contrasena = "12345", correo = "cliente@pastel.cl")
            )
            usuarios.forEach { usuarioDao.insertar(it) }


            //Insertar los productos
            val productoDao = db.productoDao()
            val productos = listOf(
                Producto(
                    nombreProducto = "Torta Cuadrada de Chocolate",
                    descripcion = "Deliciosa torta de chocolate con capas de ganache y un toque de avellanas. Personalizable con mensajes especiales.",
                    precio = 45000.0,
                    imagen = "tortachocolate.png",
                    stock = 10,
                    categoria = "Tortas Cuadradas"
                ),
                Producto(
                    nombreProducto = "Torta Cuadrada de Frutas",
                    descripcion = "Una mezcla de frutas frescas y crema chantilly sobre un suave bizcocho de vainilla, ideal para celebraciones.",
                    precio = 50000.0,
                    imagen = "tortafruta.png",
                    stock = 8,
                    categoria = "Tortas Cuadradas"
                ),
                Producto(
                    nombreProducto = "Torta Circular de Vainilla",
                    descripcion = "Bizcocho de vainilla clásico relleno con crema pastelera y cubierto con un glaseado dulce, perfecto para cualquier ocasión.",
                    precio = 40000.0,
                    imagen = "tortavainilla.png",
                    stock = 12,
                    categoria = "Tortas Circulares"
                ),
                Producto(
                    nombreProducto = "Torta Circular de Manjar",
                    descripcion = "Torta tradicional chilena con manjar y nueces, un deleite para los amantes de los sabores dulces y clásicos.",
                    precio = 42000.0,
                    imagen = "tortamanjar.png",
                    stock = 9,
                    categoria = "Tortas Circulares"
                ),
                Producto(
                    nombreProducto = "Mousse de Chocolate",
                    descripcion = "Postre individual cremoso y suave, hecho con chocolate de alta calidad, ideal para los amantes del chocolate.",
                    precio = 5000.0,
                    imagen = "mousechoco.png",
                    stock = 20,
                    categoria = "Postres Individuales"
                ),
                Producto(
                    nombreProducto = "Tiramisú Clásico",
                    descripcion = "Un postre italiano individual con capas de café, mascarpone y cacao, perfecto para finalizar cualquier comida.",
                    precio = 5500.0,
                    imagen = "tiramisuclasico.png",
                    stock = 15,
                    categoria = "Postres Individuales"
                ),
                Producto(
                    nombreProducto = "Torta Sin Azúcar de Naranja",
                    descripcion = "Torta ligera y deliciosa, endulzada naturalmente, ideal para quienes buscan opciones más saludables.",
                    precio = 48000.0,
                    imagen = "tortanaranja.png",
                    stock = 7,
                    categoria = "Productos Sin Azúcar"
                ),
                Producto(
                    nombreProducto = "Cheesecake Sin Azúcar",
                    descripcion = "Suave y cremoso, este cheesecake es una opción perfecta para disfrutar sin culpa.",
                    precio = 47000.0,
                    imagen = "cheescake.png",
                    stock = 6,
                    categoria = "Productos Sin Azúcar"
                ),
                Producto(
                    nombreProducto = "Empanada de Manzana",
                    descripcion = "Pastelería tradicional rellena de manzanas especiadas, perfecta para un dulce desayuno o merienda.",
                    precio = 3000.0,
                    imagen = "empanadademanzana.png",
                    stock = 25,
                    categoria = "Pastelería Tradicional"
                ),
                Producto(
                    nombreProducto = "Tarta de Santiago",
                    descripcion = "Tradicional tarta española hecha con almendras, azúcar y huevos, una delicia para los amantes de los postres clásicos.",
                    precio = 6000.0,
                    imagen = "tartasantiago.png",
                    stock = 10,
                    categoria = "Pastelería Tradicional"
                ),
                Producto(
                    nombreProducto = "Brownie Sin Gluten",
                    descripcion = "Rico y denso, este brownie es perfecto para quienes necesitan evitar el gluten sin sacrificar el sabor.",
                    precio = 4000.0,
                    imagen = "browniesingluten.png",
                    stock = 18,
                    categoria = "Productos Sin Gluten"
                ),
                Producto(
                    nombreProducto = "Pan Sin Gluten",
                    descripcion = "Suave y esponjoso, ideal para sándwiches o para acompañar cualquier comida.",
                    precio = 3500.0,
                    imagen = "pansingluten.png",
                    stock = 30,
                    categoria = "Productos Sin Gluten"
                ),
                Producto(
                    nombreProducto = "Torta Vegana de Chocolate",
                    descripcion = "Torta de chocolate húmeda y deliciosa, hecha sin productos de origen animal, perfecta para veganos.",
                    precio = 50000.0,
                    imagen = "chocovegano.png",
                    stock = 5,
                    categoria = "Productos Veganos"
                ),
                Producto(
                    nombreProducto = "Galletas Veganas de Avena",
                    descripcion = "Crujientes y sabrosas, estas galletas son una excelente opción para un snack saludable y vegano.",
                    precio = 4500.0,
                    imagen = "galletavegana.png",
                    stock = 40,
                    categoria = "Productos Veganos"
                ),
                Producto(
                    nombreProducto = "Torta Especial de Cumpleaños",
                    descripcion = "Diseñada especialmente para celebraciones, personalizable con decoraciones y mensajes únicos.",
                    precio = 55000.0,
                    imagen = "cumplecolorido.png",
                    stock = 4,
                    categoria = "Tortas Especiales"
                ),
                Producto(
                    nombreProducto = "Torta Especial de Boda",
                    descripcion = "Elegante y deliciosa, esta torta está diseñada para ser el centro de atención en cualquier boda.",
                    precio = 60000.0,
                    imagen = "boda.png",
                    stock = 3,
                    categoria = "Tortas Especiales"
                )

            )
            productos.forEach { productoDao.insertarProducto(it) }
        }
    }
}


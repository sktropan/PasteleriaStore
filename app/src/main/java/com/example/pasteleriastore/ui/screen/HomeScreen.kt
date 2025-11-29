package com.example.pasteleriastore.ui.screen

import android.content.Context
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pasteleriastore.R
import com.example.pasteleriastore.model.Producto
import com.example.pasteleriastore.viewmodel.ProductoViewModel

@Composable
//esta pagina es para mostrar donde estan los productos
fun HomeScreen(navController: NavController){
    val viewModel: ProductoViewModel = viewModel ()
    val productos by viewModel.productos.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.cargarProductos()

    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ){
        Text(
            text = "Bienvenidos al Rincon del Sabor",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF866909),


        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(productos){producto ->
                ProductoItem(producto = producto)
            }
        }
    }
}
@Composable
fun ProductoItem(producto: Producto){
    val context = LocalContext.current
    val imageResource = obtieneImagen(context, producto.imagen)

    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ){
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = "Imagen de ${producto.nombreProducto}",
            modifier = Modifier
                .size(100.dp)
                .padding(end = 16.dp)

        )

        Column {
            Text(
                text = producto.nombreProducto,
                fontWeight = FontWeight.Bold
            )
            Text(text = "$${producto.precio}")
            Text(
                text = producto.descripcion,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}

private fun obtieneImagen(context: Context, imagen: String?): Int{
    val nombre = imagen?.replace(".png", "") ?.replace(".jpg","") ?.replace("jpeg","") ?:"logocircular"
    val resourceId = context.resources.getIdentifier(nombre,"drawable",context.packageName)

    return if (resourceId == 0) R.drawable.logoc else resourceId
}

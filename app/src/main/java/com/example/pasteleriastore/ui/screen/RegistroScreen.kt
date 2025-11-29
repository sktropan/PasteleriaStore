package com.example.pasteleriastore.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.text.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pasteleriastore.model.Usuario

import com.example.pasteleriastore.ui.components.*
import com.example.pasteleriastore.viewmodel.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//@Preview
@Composable
fun RegistroScreen(navController: NavController){
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }

    val viewModel: LoginViewModel = viewModel()

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(24.dp)
        ){
            // Título
            TituloText(
                titulo = "Crear Cuenta",
                text = "",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Únete a los Mil Sabores",
                color = Color(0xFF9D630E),
                fontSize = 26.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Campos del formulario
            CampoTexto(
                valor = nombre,
                onValorCambio = {nombre = it},
                etiqueta = "Nombre de usuario"
            )

            Spacer(modifier = Modifier.height(16.dp))

            CampoTexto(
                valor = correo,
                onValorCambio = {correo = it},
                etiqueta = "Correo electrónico"
            )

            Spacer(modifier = Modifier.height(16.dp))

            CampoTexto(
                valor = contrasena,
                onValorCambio = {contrasena = it},
                etiqueta = "Contraseña"
            )

            Spacer(modifier = Modifier.height(16.dp))

            CampoTexto(
                valor = contrasena,
                onValorCambio = {contrasena = it},
                etiqueta = "Confirmar contraseña"
            )

            Spacer(modifier = Modifier.height(16.dp))

            BotonPastel(
                texto = "Registrarse",
                onClickAccion = {
                    CoroutineScope(Dispatchers.IO).launch {
                        val nuevoUsuario = Usuario(nombre = nombre, correo = correo,
                            contrasena = contrasena)
                        viewModel.registroUsuario(nuevoUsuario)

                         navController.navigate("login")
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            BotonPastel(
                texto = "Volver",
                onClickAccion = {
                    navController.navigate("login")
                }
            )
        }
    }

}
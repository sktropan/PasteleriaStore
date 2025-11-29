package com.example.pasteleriastore.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.Image
import androidx.glance.text.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pasteleriastore.R
import com.example.pasteleriastore.ui.components.BotonPastel
import com.example.pasteleriastore.ui.components.CampoTexto
import com.example.pasteleriastore.ui.components.TituloText
import com.example.pasteleriastore.viewmodel.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
fun LoginScreen(navController: NavController){
    var usuario by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    val viewModel: LoginViewModel = viewModel()



    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ){
        //Imagen de fondo
        Image(
            painter = painterResource(id = R.drawable.logoprincipal),
            contentDescription = "Fondo login Inicio",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.5f)
        )


        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            TituloText(
                titulo ="Bienvenido",
                text = "Iniciar sesion",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            CampoTexto(
                valor = usuario,
                onValorCambio = {usuario = it},
                etiqueta = "Usuario"

            )

            Spacer(modifier = Modifier.height(16.dp))

            CampoTexto(
                valor = contrasena,
                onValorCambio = {contrasena = it},
                etiqueta = "Contraseña",
                visualTransformation = PasswordVisualTransformation()
            )

            if(mensaje.isNotEmpty()){
                Text(
                    text = mensaje,
                    color = if(mensaje == "Login exitoso") Color.Green else Color.Red
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            //boton
            BotonPastel(
                "Ingresar",
                onClickAccion = {
                    CoroutineScope(Dispatchers.IO).launch {
                        val loginExitoso = viewModel.login(usuario, contrasena)

                        withContext(Dispatchers.Main){
                            if(loginExitoso){
                                mensaje = "Login exitoso"
                                navController.navigate("home")
                            } else{
                                mensaje = "Usuario o contraseña incorrectos"
                            }
                        }
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            BotonPastel(
                "Dulce Registro",
                onClickAccion = {
                    navController.navigate("registro")
                }
            )
        }
    }
}



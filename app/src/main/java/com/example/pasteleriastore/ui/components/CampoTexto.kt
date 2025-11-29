package com.example.pasteleriastore.ui.components

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp

@Composable
fun CampoTexto(
    valor: String,
    onValorCambio: (String) -> Unit,
    etiqueta: String,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    OutlinedTextField(
        value = valor,
        onValueChange = onValorCambio,
        label = {
            Text(
                text = etiqueta,
                color = Color(0xFF030000),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        },
        visualTransformation = visualTransformation,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color(0xFFE0A3B0),
            unfocusedIndicatorColor = Color.Gray,
            cursorColor = Color(0xFF9A5C68)
        )
    )
}
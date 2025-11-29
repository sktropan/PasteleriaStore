package com.example.pasteleriastore.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.pasteleriastore.data.PasteleriastoreDatabase
import com.example.pasteleriastore.model.Usuario

class LoginViewModel (application: Application): AndroidViewModel(application) {
    private val database = PasteleriastoreDatabase.getDatabase(application)

    private val usuarioDao = database.usuarioDao()

    suspend fun login(nombre: String, contrasena: String) : Boolean{
        val usuarioEncontrado = usuarioDao.login(nombre, contrasena)
        return usuarioEncontrado != null
    }
    suspend fun registroUsuario(usuario: Usuario): Boolean{
        return try {
            val id = usuarioDao.insertar(usuario)
            id > 0
        } catch (e: Exception){
            false
        }

    }
}
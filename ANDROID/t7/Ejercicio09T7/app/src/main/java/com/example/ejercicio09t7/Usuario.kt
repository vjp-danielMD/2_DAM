package com.example.ejercicio09t7

class Usuario(
    private var nomUsuario: String,
    private var pass: String,
    private var rol: String
) {

    fun getNomUsuario(): String {
        return nomUsuario
    }

    fun getPass(): String {
        return pass
    }

    fun getRol(): String {
        return rol
    }

    fun setNomUsuario(nomUsuario: String) {
        this.nomUsuario = nomUsuario
    }

    fun setPass(pass: String) {
        this.pass = pass
    }

    fun setRol(rol: String) {
        this.rol = rol
    }

    override fun toString(): String {
        return "Usuario(nomUsuario='$nomUsuario', rol='$rol')"
    }
}

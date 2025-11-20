package com.example.ejercicio09t7

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var arrayListUsuarios: ArrayList<Usuario>;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        arrayListUsuarios = arrayListOf(
            Usuario("root", "12345", "admin"), Usuario("pepe", "abcde", "usuario")
        )

        val edtiUser = findViewById<EditText>(R.id.editTextUser)
        val editPass = findViewById<EditText>(R.id.editTextTextPassword)
        val btnLogin = findViewById<Button>(R.id.buttonLogin)

        btnLogin.setOnClickListener {
            val user = edtiUser.text.toString()
            val pass = editPass.text.toString()

            if (user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, R.string.campo_vacio, Toast.LENGTH_SHORT).show()
            } else {
                var encontrado: Usuario? = null
                var i = 0
                while (i < arrayListUsuarios.size && encontrado == null) {
                    val u = arrayListUsuarios[i]
                    if (u.nomUsuario == user && u.pass == pass) {
                        encontrado = u
                    }
                    i++
                }

                if (encontrado != null) {
                    val intent = Intent(this, Bienvenida::class.java)
                    intent.putExtra("usuario", encontrado.nomUsuario)
                    intent.putExtra("rol", encontrado.rol)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, R.string.error_login, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
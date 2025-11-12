package com.example.ejercicio6t6

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val texto: TextView = findViewById<TextView>(R.id.textView)
        val boton: ImageButton = findViewById<ImageButton>(R.id.imageButton)
        val imagenWalter: ImageView = findViewById<ImageView>(R.id.imageView)

        boton.setOnClickListener {
            texto.setText(R.string.llamando)
            imagenWalter.setImageResource(R.drawable.heisenberg)
            boton.setImageResource(R.drawable.ringing_phone)
        }

    }
}
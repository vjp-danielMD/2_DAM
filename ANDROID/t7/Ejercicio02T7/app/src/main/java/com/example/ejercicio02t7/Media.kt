package com.example.ejercicio02t7

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Media : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_media)

        val media = intent.getDoubleExtra("media", 0.0)
        val volver: Button = findViewById<Button>(R.id.button)

        val textViewMedia: TextView = findViewById(R.id.textView2)
        textViewMedia.text = media.toString()

        volver.setOnClickListener {
            finish()
        }
    }
}
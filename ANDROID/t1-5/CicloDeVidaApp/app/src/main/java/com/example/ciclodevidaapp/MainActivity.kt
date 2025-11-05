package com.example.ciclodevidaapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onStart() {
        super.onStart()
        val texto: String = getResources().getString(R.string.texto_onstart);
        Log.d("TAG", texto)
    }

    override fun onPause() {
        super.onPause()
        val texto: String = getResources().getString(R.string.texto_onpause);
        Log.d("TAG", texto);
    }

    override fun onResume() {
        super.onResume()
        val texto: String = getResources().getString(R.string.texto_onresume);
        Log.d("TAG", texto);
    }

}
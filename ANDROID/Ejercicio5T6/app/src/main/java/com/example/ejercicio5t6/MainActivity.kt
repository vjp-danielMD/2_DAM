package com.example.ejercicio5t6

import android.os.Bundle
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    lateinit var toggleButton: ToggleButton
    lateinit var switchGafas: Switch
    lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toggleButton = findViewById(R.id.toggleButton)
        switchGafas = findViewById(R.id.switch1)
        imageView = findViewById(R.id.imageView)

        toggleButton.setOnCheckedChangeListener(toggleListener)
        switchGafas.setOnCheckedChangeListener(switchListener)
    }

    private val toggleListener = CompoundButton.OnCheckedChangeListener { _, _ ->
        actualizarImagen()
    }

    private val switchListener = CompoundButton.OnCheckedChangeListener { _, _ ->
        actualizarImagen()
    }

    private fun actualizarImagen() {
        if (toggleButton.isChecked && switchGafas.isChecked) {
            imageView.setImageResource(R.drawable.potatogs)
        } else if (toggleButton.isChecked && !switchGafas.isChecked) {
            imageView.setImageResource(R.drawable.potatos)
        } else if (!toggleButton.isChecked && switchGafas.isChecked) {
            imageView.setImageResource(R.drawable.potatog)
        } else {
            imageView.setImageResource(R.drawable.potato)
        }
    }
}

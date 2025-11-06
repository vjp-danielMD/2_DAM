package com.example.ejercicio3t6

import android.os.Bundle
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val radioGroup: RadioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val selectedRadioButtonId = radioGroup.checkedRadioButtonId
        val textView: TextView = findViewById<TextView>(R.id.textView)

        if (selectedRadioButtonId == -1){
            textView.setText("No se ha seleccionado nada")
        }else if (selectedRadioButtonId == R.id.radioButton){
            textView.setText("@strin")
        }

    }
}
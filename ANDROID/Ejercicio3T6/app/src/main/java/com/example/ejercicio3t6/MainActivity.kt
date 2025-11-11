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

        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val textView = findViewById<TextView>(R.id.textView)

        val selectedId = radioGroup.checkedRadioButtonId
        if (selectedId == -1) {
            textView.text = getString(R.string.no_seleccion)
        } else if (selectedId == R.id.radioButton) {
            textView.text = getString(R.string.masculino)
        } else if (selectedId == R.id.radioButton2) {
            textView.text = getString(R.string.femenino)
        }

        radioGroup.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup, checkedId: Int) {
                if (checkedId == R.id.radioButton) {
                    textView.text = getString(R.string.masculino)
                } else if (checkedId == R.id.radioButton2) {
                    textView.text = getString(R.string.femenino)
                } else {
                    textView.text = getString(R.string.no_seleccion)
                }
            }
        })
    }
}

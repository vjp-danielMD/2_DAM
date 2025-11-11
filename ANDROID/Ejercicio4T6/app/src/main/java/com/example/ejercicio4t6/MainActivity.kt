package com.example.ejercicio4t6

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText1 = findViewById<EditText>(R.id.editTextNumber)
        val editText2 = findViewById<EditText>(R.id.editTextNumber2)
        val checkBoxSumar = findViewById<CheckBox>(R.id.checkBox)
        val checkBoxRestar = findViewById<CheckBox>(R.id.checkBox2)
        val buttonOperar = findViewById<Button>(R.id.button)
        val textViewSuma = findViewById<TextView>(R.id.textView2)
        val textViewResta = findViewById<TextView>(R.id.textView3)

        buttonOperar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: android.view.View?) {
                val valor1 = editText1.text.toString().toIntOrNull()
                val valor2 = editText2.text.toString().toIntOrNull()

                if (valor1 != null && valor2 != null) {
                    if (checkBoxSumar.isChecked) {
                        val suma = valor1 + valor2
                        textViewSuma.text = getString(R.string.resultado_suma) + " " + suma
                    }

                    if (checkBoxRestar.isChecked) {
                        val resta = valor1 - valor2
                        textViewResta.text = getString(R.string.resultado_resta) + " " + resta
                    }

                    if (!checkBoxSumar.isChecked && !checkBoxRestar.isChecked) {
                        textViewSuma.text = ""
                        textViewResta.text = ""
                    }
                } else {
                    textViewSuma.text = getString(R.string.error)
                    textViewResta.text = ""
                }
            }
        })
    }
}

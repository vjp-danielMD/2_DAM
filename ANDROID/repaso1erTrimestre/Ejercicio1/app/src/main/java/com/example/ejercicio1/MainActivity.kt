package com.example.ejercicio1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var editPeso: EditText
    private lateinit var editAltura: EditText
    private lateinit var btnCalcular: Button
    private val historial = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        editPeso = findViewById(R.id.editTextPeso)
        editAltura = findViewById(R.id.editTextEstatura)
        btnCalcular = findViewById(R.id.buttonCalcular)

        btnCalcular.setOnClickListener {
            calcularIMC()
        }
    }

    private fun calcularIMC() {
        val peso = editPeso.text.toString().toDoubleOrNull()
        val altura = editAltura.text.toString().toDoubleOrNull()

        if (peso == null || altura == null || altura == 0.0) {
            Toast.makeText(this, "Ingresa valores válidos", Toast.LENGTH_SHORT).show()
            return
        }

        val imc = peso / (altura * altura)
        val resultado = "Peso: $peso kg\nAltura: $altura m\nIMC: %.2f".format(imc)
        historial.add(resultado)

        AlertDialog.Builder(this).setTitle("Resultado del IMC").setMessage(resultado).setPositiveButton("OK", null).show()
    }
}
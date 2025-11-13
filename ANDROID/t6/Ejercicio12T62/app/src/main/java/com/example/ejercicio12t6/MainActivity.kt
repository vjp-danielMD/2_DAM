package com.example.ejercicio12t6

import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val grupoPrimera: RadioGroup = findViewById<RadioGroup>(R.id.grupoPrimera)
        val grupoSegunda: RadioGroup = findViewById<RadioGroup>(R.id.grupoSegunda)
        val grupoTercera: RadioGroup = findViewById<RadioGroup>(R.id.grupoTercera)
        val botonEnviar: Button = findViewById<Button>(R.id.buttonSend)

        botonEnviar.setOnClickListener {
            var aciertos = 0;
            val total = 3;

            //  respuestas correctas
            val idPrimera = grupoPrimera.checkedRadioButtonId
            val idSegunda = grupoSegunda.checkedRadioButtonId
            val idTercera = grupoTercera.checkedRadioButtonId

            if (idPrimera != -1) {
                val seleccion: RadioButton = findViewById<RadioButton>(idPrimera)
                if (seleccion.text == getString(R.string.primera_op_uno)) aciertos++
            }

            if (idSegunda != -1) {
                val seleccion = findViewById<RadioButton>(idSegunda)
                if (seleccion.text == getString(R.string.segunda_op_uno)) aciertos++
            }

            if (idTercera != -1) {
                val seleccion = findViewById<RadioButton>(idTercera)
                if (seleccion.text == getString(R.string.tercera_op_tres)) aciertos++
            }

            val porcentaje = (aciertos.toDouble() / total * 100).toInt()

            val mensaje = getString(R.string.toast, porcentaje)

            Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()

        }
    }
}


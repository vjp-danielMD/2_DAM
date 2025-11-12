package com.example.ejercicio11t6

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private var numAleatorio = 0
    private var intentos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextNum = findViewById<EditText>(R.id.editTextNumber)
        val textViewIntentosNum = findViewById<TextView>(R.id.textViewIntentosNum)
        val textViewWin = findViewById<TextView>(R.id.textViewWIn)
        val textViewIntentosFinal = findViewById<TextView>(R.id.textViewIntentosFinal)
        val buttonSuerte = findViewById<Button>(R.id.buttonSuerte)
        val buttonReset = findViewById<Button>(R.id.buttonReset)

        iniciarJuego()

        buttonSuerte.setOnClickListener {
            val input = editTextNum.text.toString()
            if (input.isNotEmpty()) {
                val numeroUsuario = input.toInt()
                intentos++
                textViewIntentosNum.text = intentos.toString()

                when {
                    numeroUsuario < numAleatorio -> {
                        Toast.makeText(this, R.string.pista, Toast.LENGTH_SHORT).show()
                    }
                    numeroUsuario > numAleatorio -> {
                        Toast.makeText(this, R.string.pista_dos, Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        textViewWin.text = getString(R.string.win)
                        textViewIntentosFinal.text = getString(R.string.intentos_final, intentos.toString())
                    }
                }
            } else {
                Toast.makeText(this, R.string.introduce, Toast.LENGTH_SHORT).show()
            }
        }

        buttonReset.setOnClickListener {
            iniciarJuego()
            editTextNum.text.clear()
            textViewIntentosNum.text = ""
            textViewWin.text = ""
            textViewIntentosFinal.text = ""
        }
    }

    private fun iniciarJuego() {
        numAleatorio = Random.nextInt(1, 11)
        intentos = 0
    }
}

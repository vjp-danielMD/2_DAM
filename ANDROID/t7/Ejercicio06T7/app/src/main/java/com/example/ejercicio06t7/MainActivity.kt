package com.example.ejercicio06t7

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var editTextURL: EditText
    lateinit var editTextLongitud: EditText
    lateinit var editTextLatitud: EditText
    lateinit var editTextEmail: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        editTextURL = findViewById(R.id.editTextURL)
        editTextLongitud = findViewById(R.id.editTextLONGITUD)
        editTextLatitud = findViewById(R.id.editTextLATITUD)
        editTextEmail = findViewById(R.id.editTextTextEmailAddress)

        val btnIr: Button = findViewById<Button>(R.id.btnIr)
        val btnVer: Button = findViewById<Button>(R.id.btnVer)
        val btnEnviar: Button = findViewById<Button>(R.id.btnEnviar)



        btnIr.setOnClickListener(this)
        btnVer.setOnClickListener(this)
        btnEnviar.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnIr -> visitarURL()
            R.id.btnVer -> visitarGoogleMaps()
            R.id.btnEnviar -> enviarMail()
        }
    }

    private fun visitarURL() {
        val URL: String? = editTextURL.text.toString()

        if (URL == null || URL == "") {
            Toast.makeText(applicationContext, R.string.msg_no_url, Toast.LENGTH_SHORT).show()
        } else {
            val intent: Intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(URL))
            startActivity(intent)
        }
    }

    private fun visitarGoogleMaps() {
        val latitud: String? = editTextLatitud.text.toString()
        val longitud: String? = editTextLongitud.text.toString()

        if (latitud == null || longitud == null) {
            Toast.makeText(applicationContext, R.string.msg_no_coordenadas, Toast.LENGTH_SHORT)
                .show()
        } else {
            val intent: Intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse("geo:$latitud,$longitud"))
            startActivity(intent)
        }
    }

    private fun enviarMail() {
        val destinatario: String? = editTextEmail.text.toString()
        if (destinatario == null || destinatario == "") {
            Toast.makeText(applicationContext, R.string.msg_no_email, Toast.LENGTH_SHORT).show()
        } else {
            val asunto: String = "Mi primer email"
            val texto: String = "Hola, este es mi primer email enviado desde una app"
            val type: String = "text/plain"

            val intent: Intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_EMAIL, destinatario)
            intent.putExtra(Intent.EXTRA_SUBJECT, asunto)
            intent.putExtra(Intent.EXTRA_TEXT, texto)
            intent.type = type
            startActivity(intent)
        }

    }
}






















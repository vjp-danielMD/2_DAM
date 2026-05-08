package com.example.pruebafirebase

import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Configuración de la referencia
        dbRef = FirebaseDatabase.getInstance("https://prueba-firebase-8965f-default-rtdb.europe-west1.firebasedatabase.app/")
            .getReference("coches")

        val etMarca = findViewById<EditText>(R.id.etMarca)
        val etModelo = findViewById<EditText>(R.id.etModelo)
        val etPuertas = findViewById<EditText>(R.id.etPuertas)
        val etVelocidad = findViewById<EditText>(R.id.etVelocidad)
        val btnInsertar = findViewById<Button>(R.id.btnInsertar)
        val tvListado = findViewById<TextView>(R.id.tvListado)

        // 2. Insertar datos
        btnInsertar.setOnClickListener {
            val coche = Coche(
                etMarca.text.toString(),
                etModelo.text.toString(),
                etPuertas.text.toString().toIntOrNull() ?: 0,
                etVelocidad.text.toString().toIntOrNull() ?: 0
            )

            dbRef.push().setValue(coche).addOnSuccessListener {
                Toast.makeText(this, "¡Coche insertado!", Toast.LENGTH_SHORT).show()
                etMarca.text.clear()
                etModelo.text.clear()
                etPuertas.text.clear()
                etVelocidad.text.clear()
            }
        }

        // 3. Leer datos en tiempo real
        val cochesListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val sb = StringBuilder()
                // Recorremos los nodos hijos
                for (postSnapshot in snapshot.children) {
                    val c = postSnapshot.getValue(Coche::class.java)
                    if (c != null) {
                        sb.append("${c.marca} ${c.modelo}\n")
                        sb.append("   Puertas: ${c.numeroPuertas} | Máx: ${c.velocidadMaxima} km/h\n\n")
                    }
                }
                // Actualizamos el TextView
                tvListado.text = sb.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Error de lectura: ${error.message}")
            }
        }

        // Aplicamos el listener a la referencia
        dbRef.addValueEventListener(cochesListener)
    }
}
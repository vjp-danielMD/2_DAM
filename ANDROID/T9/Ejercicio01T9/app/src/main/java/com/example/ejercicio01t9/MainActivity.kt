package com.example.ejercicio01t9

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.ejercicio01t9.controladores.DeviceController
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    // Referencias a los elementos de la UI
    private lateinit var editTextId: EditText
    private lateinit var buttonConsultar: Button
    private lateinit var textViewName: TextView
    private lateinit var textViewColor: TextView
    private lateinit var textViewPrice: TextView

    // Controlador (patrón MVC)
    private val deviceController = DeviceController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializar las vistas
        inicializarVistas()

        // Configurar el listener del botón
        configurarBotonConsultar()
    }

    private fun inicializarVistas() {
        editTextId = findViewById(R.id.editTextNumber)
        buttonConsultar = findViewById(R.id.buttonConsultar)
        textViewName = findViewById(R.id.textViewName)
        textViewColor = findViewById(R.id.textViewColor)
        textViewPrice = findViewById(R.id.textViewPrice)
    }

    private fun configurarBotonConsultar() {
        buttonConsultar.setOnClickListener {
            val id = editTextId.text.toString()

            // Validar que se haya ingresado un ID
            if (id.isEmpty()) {
                Toast.makeText(this, getString(R.string.por_favor_ingresa_un_id), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Realizar la petición HTTP en una corrutina
            consultarDispositivo(id)
        }
    }

    private fun consultarDispositivo(id: String) {
        // Lanzar una corrutina en el scope del ciclo de vida de la Activity
        lifecycleScope.launch {
            try {
                // Mostrar mensaje de carga
                Toast.makeText(this@MainActivity,
                    getString(R.string.consultando), Toast.LENGTH_SHORT).show()

                // Llamar al controlador para obtener el dispositivo
                val device = deviceController.obtenerDispositivoPorId(id)

                // Actualizar la UI con los datos obtenidos
                if (device != null) {
                    actualizarUI(device)
                } else {
                    mostrarError(getString(R.string.no_se_encontr_el_dispositivo_con_id, id))
                    limpiarUI()
                }
            } catch (e: Exception) {
                mostrarError(getString(R.string.error_al_consultar, e.message))
                limpiarUI()
            }
        }
    }

    private fun actualizarUI(device: com.example.ejercicio01t9.modelos.Device) {
        // Actualizar el nombre
        textViewName.text = device.name

        // Actualizar el color
        textViewColor.text = device.data?.color

        // Actualizar el precio 
        val precio = device.data?.price?.toString()
        textViewPrice.text = precio

        Toast.makeText(this, getString(R.string.datos_cargados_correctamente), Toast.LENGTH_SHORT).show()
    }

    private fun limpiarUI() {
        textViewName.text = ""
        textViewColor.text = ""
        textViewPrice.text = ""
    }

    private fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}
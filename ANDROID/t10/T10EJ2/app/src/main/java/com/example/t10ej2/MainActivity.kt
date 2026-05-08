package com.example.t10ej2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.t10ej2.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getDatabase(this)

        binding.btnAnadir.setOnClickListener {
            val articulo = getArticuloFromFields()
            if (articulo != null) {
                lifecycleScope.launch {
                    db.articuloDao().insertar(articulo)
                    Toast.makeText(this@MainActivity, "Artículo añadido", Toast.LENGTH_SHORT).show()
                    limpiarCampos()
                }
            }
        }

        binding.btnBorrar.setOnClickListener {
            val codigoStr = binding.etCodigo.text.toString()
            if (codigoStr.isNotEmpty()) {
                lifecycleScope.launch {
                    val articulo = db.articuloDao().consultarPorCodigo(codigoStr.toInt())
                    if (articulo != null) {
                        db.articuloDao().borrar(articulo)
                        Toast.makeText(this@MainActivity, "Artículo borrado", Toast.LENGTH_SHORT).show()
                        limpiarCampos()
                    } else {
                        Toast.makeText(this@MainActivity, "No encontrado", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        binding.btnModificar.setOnClickListener {
            val articulo = getArticuloFromFields()
            if (articulo != null) {
                lifecycleScope.launch {
                    db.articuloDao().actualizar(articulo)
                    Toast.makeText(this@MainActivity, "Artículo actualizado", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnBuscarCodigo.setOnClickListener {
            val codigoStr = binding.etCodigo.text.toString()
            if (codigoStr.isNotEmpty()) {
                lifecycleScope.launch {
                    val articulo = db.articuloDao().consultarPorCodigo(codigoStr.toInt())
                    if (articulo != null) {
                        rellenarCampos(articulo)
                    } else {
                        Toast.makeText(this@MainActivity, "No encontrado", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        binding.btnBuscarDescripcion.setOnClickListener {
            val desc = binding.etDescripcion.text.toString()
            if (desc.isNotEmpty()) {
                lifecycleScope.launch {
                    val lista = db.articuloDao().consultarPorDescripcion(desc)
                    if (lista.isNotEmpty()) {
                        rellenarCampos(lista[0]) // Mostramos el primero encontrado
                        Toast.makeText(this@MainActivity, "Encontrados: ${lista.size}", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@MainActivity, "Sin resultados", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        binding.btnConsultarTodos.setOnClickListener {
            val intent = Intent(this, ListadoActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getArticuloFromFields(): Articulo? {
        val codigo = binding.etCodigo.text.toString()
        val desc = binding.etDescripcion.text.toString()
        val stock = binding.etStock.text.toString()
        val precio = binding.etPrecio.text.toString()

        if (codigo.isEmpty() || desc.isEmpty() || stock.isEmpty() || precio.isEmpty()) {
            Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show()
            return null
        }

        return Articulo(codigo.toInt(), desc, stock.toInt(), precio.toDouble())
    }

    private fun rellenarCampos(articulo: Articulo) {
        binding.etCodigo.setText(articulo.codigo.toString())
        binding.etDescripcion.setText(articulo.descripcion)
        binding.etStock.setText(articulo.stock.toString())
        binding.etPrecio.setText(articulo.precio.toString())
    }

    private fun limpiarCampos() {
        binding.etCodigo.text.clear()
        binding.etDescripcion.text.clear()
        binding.etStock.text.clear()
        binding.etPrecio.text.clear()
    }
}
package com.iesvjp.galeriadeswann.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.iesvjp.galeriadeswann.databinding.FragmentGalleryBinding
import com.iesvjp.galeriadeswann.modelo.AppDatabase
import com.iesvjp.galeriadeswann.modelo.Articulo
import kotlinx.coroutines.launch

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)

        binding.btnGuardarArticulo.setOnClickListener {
            validarYGuardar()
        }

        return binding.root
    }

    private fun validarYGuardar() {
        val nombre = binding.etNombreArticulo.text.toString()
        val precioStr = binding.etPrecioArticulo.text.toString()
        val unidadesStr = binding.etUnidadesArticulo.text.toString()

        // VALIDACIÓN: Comprobar que no haya campos vacíos
        if (nombre.isEmpty() || precioStr.isEmpty() || unidadesStr.isEmpty()) {
            Toast.makeText(requireContext(), "Por favor, rellena todos los datos", Toast.LENGTH_SHORT).show()
            return
        }

        val precio = precioStr.toDouble()
        val unidades = unidadesStr.toInt()

        // Creamos el objeto Articulo (el ID es 0 porque es auto-generado)
        val nuevoArticulo = Articulo(nombre = nombre, precio = precio, unidades = unidades)

        // ASINCRONÍA: Usamos Corrutinas con lifecycleScope
        lifecycleScope.launch {
            try {
                val db = AppDatabase.getDatabase(requireContext())
                db.articuloDao().insertar(nuevoArticulo)

                // FEEDBACK al usuario
                Toast.makeText(requireContext(), "Artículo añadido con éxito", Toast.LENGTH_SHORT).show()

                // Limpiar formulario
                binding.etNombreArticulo.text.clear()
                binding.etPrecioArticulo.text.clear()
                binding.etUnidadesArticulo.text.clear()

            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Error al guardar: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
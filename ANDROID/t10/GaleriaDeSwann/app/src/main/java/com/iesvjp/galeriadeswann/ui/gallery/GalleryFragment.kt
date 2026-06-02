package com.iesvjp.galeriadeswann.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.iesvjp.galeriadeswann.R
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
        val nombre = binding.etNombreArticulo.text.toString().trim()
        val precioStr = binding.etPrecioArticulo.text.toString().trim()
        val unidadesStr = binding.etUnidadesArticulo.text.toString().trim()

        if (nombre.isEmpty() || precioStr.isEmpty() || unidadesStr.isEmpty()) {
            Toast.makeText(requireContext(), getString(R.string.toast_rellena_datos), Toast.LENGTH_SHORT).show()
            return
        }

        val precio = precioStr.toDoubleOrNull()
        val unidades = unidadesStr.toIntOrNull()

        if (precio == null || unidades == null) {
            Toast.makeText(requireContext(), getString(R.string.toast_valores_invalidos), Toast.LENGTH_SHORT).show()
            return
        }

        val nuevoArticulo = Articulo(nombre = nombre, precio = precio, unidades = unidades)

        lifecycleScope.launch {
            try {
                val db = AppDatabase.getDatabase(requireContext())
                db.articuloDao().insertar(nuevoArticulo)

                Toast.makeText(requireContext(), getString(R.string.toast_articulo_anadido), Toast.LENGTH_SHORT).show()

                binding.etNombreArticulo.setText("")
                binding.etPrecioArticulo.setText("")
                binding.etUnidadesArticulo.setText("")

            } catch (e: Exception) {
                Toast.makeText(requireContext(), getString(R.string.toast_error_guardar, e.message), Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.iesvjp.galeriadeswann.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.iesvjp.galeriadeswann.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    // Nombre del archivo de preferencias
    private val PREFS_NAME = "PrefsSwann"
    private val KEY_NEGOCIO = "nombre_negocio"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // 1. Cargar el nombre al iniciar
        cargarNombre()

        // 2. Configurar el botón de guardar
        binding.btnGuardarNombre.setOnClickListener {
            val nuevoNombre = binding.etNuevoNombre.text.toString()

            if (nuevoNombre.isNotEmpty()) {
                guardarNombre(nuevoNombre)
                Toast.makeText(requireContext(), "Nombre actualizado con éxito", Toast.LENGTH_SHORT).show()
                binding.etNuevoNombre.text.clear()
            } else {
                Toast.makeText(requireContext(), "Por favor, escribe un nombre", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    private fun guardarNombre(nombre: String) {
        val prefs = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString(KEY_NEGOCIO, nombre)
        editor.apply()

        binding.tvNombreActual.text = nombre
    }

    private fun cargarNombre() {
        val prefs = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val nombreGuardado = prefs.getString(KEY_NEGOCIO, "Galería de Charles Swann")
        binding.tvNombreActual.text = nombreGuardado
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
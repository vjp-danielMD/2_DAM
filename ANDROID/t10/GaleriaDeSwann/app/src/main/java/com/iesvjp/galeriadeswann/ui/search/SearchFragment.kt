package com.iesvjp.galeriadeswann.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.iesvjp.galeriadeswann.databinding.FragmentSearchBinding
import com.iesvjp.galeriadeswann.modelo.AppDatabase
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        binding.btnBuscar.setOnClickListener {
            realizarBusqueda()
        }

        return binding.root
    }

    private fun realizarBusqueda() {
        val idStr = binding.etBuscarId.text.toString()

        // VALIDACIÓN: Comprobar que no esté vacío
        if (idStr.isEmpty()) {
            Toast.makeText(requireContext(), "Por favor, rellena todos los datos", Toast.LENGTH_SHORT).show()
            binding.cardResultado.visibility = View.GONE
            return
        }

        val idBuscar = idStr.toInt()

        // ASINCRONÍA: Corrutinas para buscar en la BD
        viewLifecycleOwner.lifecycleScope.launch {
            val db = AppDatabase.getDatabase(requireContext())
            val articulo = db.articuloDao().obtenerPorId(idBuscar) // Nota: Si tu DAO se llama obtenerPorId, cámbialo aquí

            if (articulo != null) {
                // Si existe, mostramos la tarjeta y rellenamos los TextViews
                binding.cardResultado.visibility = View.VISIBLE
                binding.tvResultadoNombre.text = articulo.nombre
                binding.tvResultadoPrecio.text = "Precio: ${articulo.precio} €"
                binding.tvResultadoUnidades.text = "Unidades en Stock: ${articulo.unidades}"
            } else {
                // FEEDBACK si no existe
                binding.cardResultado.visibility = View.GONE
                Toast.makeText(requireContext(), "Error: el ID introducido no existe", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
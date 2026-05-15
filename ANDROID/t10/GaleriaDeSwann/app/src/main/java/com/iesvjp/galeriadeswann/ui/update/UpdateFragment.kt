package com.iesvjp.galeriadeswann.ui.update

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.iesvjp.galeriadeswann.databinding.FragmentUpdateBinding
import com.iesvjp.galeriadeswann.modelo.AppDatabase
import com.iesvjp.galeriadeswann.modelo.Articulo
import kotlinx.coroutines.launch

class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!
    private var articuloActual: Articulo? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)

        // Botón para buscar el artículo por ID antes de editar
        binding.btnBuscarUpdate.setOnClickListener {
            buscarParaModificar()
        }

        // Botón para procesar la actualización en la BD
        binding.btnActualizar.setOnClickListener {
            actualizarArticulo()
        }

        return binding.root
    }

    private fun buscarParaModificar() {
        val idStr = binding.etUpdateIdBuscar.text.toString()
        if (idStr.isEmpty()) {
            Toast.makeText(requireContext(), "Por favor, rellena todos los datos", Toast.LENGTH_SHORT).show()
            binding.layoutFormularioUpdate.visibility = View.GONE
            return
        }

        viewLifecycleOwner.lifecycleScope.launch {
            val db = AppDatabase.getDatabase(requireContext())
            val articulo = db.articuloDao().obtenerPorId(idStr.toInt())

            if (articulo != null) {
                articuloActual = articulo
                binding.layoutFormularioUpdate.visibility = View.VISIBLE
                binding.etUpdateNombre.setText(articulo.nombre)
                binding.etUpdatePrecio.setText(articulo.precio.toString())
                binding.etUpdateUnidades.setText(articulo.unidades.toString())
            } else {
                binding.layoutFormularioUpdate.visibility = View.GONE
                Toast.makeText(requireContext(), "Error: el ID introducido no existe", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun actualizarArticulo() {
        val nombre = binding.etUpdateNombre.text.toString()
        val precioStr = binding.etUpdatePrecio.text.toString()
        val unidadesStr = binding.etUpdateUnidades.text.toString()

        if (nombre.isEmpty() || precioStr.isEmpty() || unidadesStr.isEmpty()) {
            Toast.makeText(requireContext(), "Por favor, rellena todos los datos", Toast.LENGTH_SHORT).show()
            return
        }

        viewLifecycleOwner.lifecycleScope.launch {
            val db = AppDatabase.getDatabase(requireContext())
            // Creamos un nuevo objeto manteniendo el mismo ID original para que Room sepa cuál sobreescribir
            val articuloModificado = Articulo(
                id = articuloActual!!.id,
                nombre = nombre,
                precio = precioStr.toDouble(),
                unidades = unidadesStr.toInt()
            )

            db.articuloDao().actualizar(articuloModificado)
            Toast.makeText(requireContext(), "Artículo modificado con éxito", Toast.LENGTH_SHORT).show()

            // Limpiar y ocultar
            binding.layoutFormularioUpdate.visibility = View.GONE
            binding.etUpdateIdBuscar.text.clear()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
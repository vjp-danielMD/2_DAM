package com.iesvjp.galeriadeswann.ui.update

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.iesvjp.galeriadeswann.R
import com.iesvjp.galeriadeswann.databinding.FragmentUpdateBinding
import com.iesvjp.galeriadeswann.controlador.ArticuloController
import com.iesvjp.galeriadeswann.modelo.Articulo
import kotlinx.coroutines.launch

class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!
    private var articuloActual: Articulo? = null
    private lateinit var articuloController: ArticuloController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        articuloController = ArticuloController(requireContext())

        // preparar busqueda
        binding.btnBuscarUpdate.setOnClickListener {
            buscarParaModificar()
        }

        // preparar actualizacion
        binding.btnActualizar.setOnClickListener {
            actualizarArticulo()
        }

        return binding.root
    }

    private fun buscarParaModificar() {
        val idStr = binding.etUpdateIdBuscar.text.toString()
        if (idStr.isEmpty()) {
            Toast.makeText(requireContext(), getString(R.string.toast_rellena_datos), Toast.LENGTH_SHORT).show()
            binding.layoutFormularioUpdate.visibility = View.GONE
            return
        }

        viewLifecycleOwner.lifecycleScope.launch {
            val articulo = articuloController.obtenerPorId(idStr.toInt())

            if (articulo != null) {
                articuloActual = articulo
                binding.layoutFormularioUpdate.visibility = View.VISIBLE
                binding.etUpdateNombre.setText(articulo.nombre)
                binding.etUpdatePrecio.setText(articulo.precio.toString())
                binding.etUpdateUnidades.setText(articulo.unidades.toString())
            } else {
                binding.layoutFormularioUpdate.visibility = View.GONE
                Toast.makeText(requireContext(), getString(R.string.toast_error_id_no_existe), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun actualizarArticulo() {
        val nombre = binding.etUpdateNombre.text.toString()
        val precioStr = binding.etUpdatePrecio.text.toString()
        val unidadesStr = binding.etUpdateUnidades.text.toString()

        if (nombre.isEmpty() || precioStr.isEmpty() || unidadesStr.isEmpty()) {
            Toast.makeText(requireContext(), getString(R.string.toast_rellena_datos), Toast.LENGTH_SHORT).show()
            return
        }

        viewLifecycleOwner.lifecycleScope.launch {
            val articuloModificado = Articulo(
                id = articuloActual!!.id,
                nombre = nombre,
                precio = precioStr.toDouble(),
                unidades = unidadesStr.toInt()
            )

            articuloController.actualizar(articuloModificado)
            Toast.makeText(requireContext(), getString(R.string.toast_articulo_modificado), Toast.LENGTH_SHORT).show()

            binding.layoutFormularioUpdate.visibility = View.GONE
            binding.etUpdateIdBuscar.text?.clear()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
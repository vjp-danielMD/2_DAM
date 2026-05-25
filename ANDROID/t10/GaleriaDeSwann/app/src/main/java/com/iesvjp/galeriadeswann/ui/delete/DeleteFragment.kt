package com.iesvjp.galeriadeswann.ui.delete

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.iesvjp.galeriadeswann.R
import com.iesvjp.galeriadeswann.databinding.FragmentDeleteBinding
import com.iesvjp.galeriadeswann.controlador.ArticuloController
import kotlinx.coroutines.launch

class DeleteFragment : Fragment() {

    private var _binding: FragmentDeleteBinding? = null
    private val binding get() = _binding!!
    private lateinit var articuloController: ArticuloController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeleteBinding.inflate(inflater, container, false)
        articuloController = ArticuloController(requireContext())

        binding.btnEliminar.setOnClickListener {
            eliminarPorId()
        }

        return binding.root
    }

    private fun eliminarPorId() {
        val idStr = binding.etDeleteId.text.toString().trim()

        if (idStr.isEmpty()) {
            Toast.makeText(requireContext(), getString(R.string.toast_rellena_datos), Toast.LENGTH_SHORT).show()
            return
        }

        val idEliminar = idStr.toIntOrNull()
        if (idEliminar == null) {
            Toast.makeText(requireContext(), getString(R.string.toast_id_invalido), Toast.LENGTH_SHORT).show()
            return
        }

        viewLifecycleOwner.lifecycleScope.launch {
            val articulo = articuloController.obtenerPorId(idEliminar)

            if (articulo != null) {
                articuloController.eliminarPorId(idEliminar)
                Toast.makeText(requireContext(), getString(R.string.toast_articulo_eliminado), Toast.LENGTH_SHORT).show()

                binding.etDeleteId.setText("")
            } else {
                Toast.makeText(requireContext(), getString(R.string.toast_error_id_no_existe), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
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
import com.iesvjp.galeriadeswann.modelo.AppDatabase
import kotlinx.coroutines.launch

class DeleteFragment : Fragment() {

    private var _binding: FragmentDeleteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeleteBinding.inflate(inflater, container, false)

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

        lifecycleScope.launch {
            val db = AppDatabase.getDatabase(requireContext())
            val articulo = db.articuloDao().obtenerPorId(idEliminar)

            if (articulo != null) {
                db.articuloDao().eliminarPorId(idEliminar)
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
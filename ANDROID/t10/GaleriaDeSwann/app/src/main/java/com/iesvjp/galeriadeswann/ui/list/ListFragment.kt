package com.iesvjp.galeriadeswann.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.iesvjp.galeriadeswann.databinding.FragmentListBinding
import com.iesvjp.galeriadeswann.modelo.AppDatabase
import com.iesvjp.galeriadeswann.ui.ArticuloAdapter
import kotlinx.coroutines.launch

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adaptador: ArticuloAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // inicializar binding y controlador
        _binding = FragmentListBinding.inflate(inflater, container, false)

        configurarRecyclerView()
        cargarArticulos()

        return binding.root
    }

    private fun configurarRecyclerView() {
        // configurar lista basica
        adaptador = ArticuloAdapter(emptyList()) { articulo ->
            // sin accion al hacer clic
        }
        binding.rvArticulos.layoutManager = LinearLayoutManager(requireContext())
        binding.rvArticulos.adapter = adaptador
    }

    private fun cargarArticulos() {
        lifecycleScope.launch {
            val db = AppDatabase.getDatabase(requireContext())
            val lista = db.articuloDao().obtenerTodos()

            if (lista.isEmpty()) {
                binding.tvListaVacia.visibility = View.VISIBLE
                binding.rvArticulos.visibility = View.GONE
            } else {
                binding.tvListaVacia.visibility = View.GONE
                binding.rvArticulos.visibility = View.VISIBLE
                adaptador.actualizarDatos(lista)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
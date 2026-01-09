package com.example.practicat9.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practicat9.R
import com.example.practicat9.databinding.FragmentHomeBinding
import com.example.practicat9.servicios.ClienteRetrofit
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var spellAdapter: SpellAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var tvSpellCount: TextView
    private lateinit var tvError: TextView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializar vistas
        recyclerView = binding.recyclerViewSpells
        progressBar = binding.progressBar
        tvSpellCount = binding.tvSpellCount
        tvError = binding.tvError

        // Configurar RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        spellAdapter = SpellAdapter(emptyList())
        recyclerView.adapter = spellAdapter

        // Cargar hechizos
        loadAllSpells()
    }

    /** Carga todos los hechizos usando Coroutines */
    private fun loadAllSpells() {
        // Mostrar loading
        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
        tvError.visibility = View.GONE

        // Lanzar coroutina en el ciclo de vida del Fragment
        lifecycleScope.launch {
            try {
                val response = ClienteRetrofit.apiService.getAllSpells()

                // Ocultar loading
                progressBar.visibility = View.GONE

                if (response.isSuccessful) {
                    val spells = response.body()
                    if (spells != null && spells.isNotEmpty()) {
                        // Mostrar hechizos
                        spellAdapter.updateSpellsList(spells)
                        recyclerView.visibility = View.VISIBLE
                        tvSpellCount.text = getString(R.string.spell_count, spells.size)
                        Toast.makeText(
                                        requireContext(),
                                        getString(R.string.spell_count, spells.size),
                                        Toast.LENGTH_SHORT
                                )
                                .show()
                    } else {
                        // Lista vacía
                        tvError.text = getString(R.string.no_spells)
                        tvError.visibility = View.VISIBLE
                    }
                } else {
                    // Error HTTP
                    val errorMsg =
                            when (response.code()) {
                                404 -> getString(R.string.no_spells)
                                500 -> getString(R.string.server_error)
                                else -> "${getString(R.string.unknown_error)} (${response.code()})"
                            }
                    tvError.text = errorMsg
                    tvError.visibility = View.VISIBLE
                    Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                // Error de red o excepción
                progressBar.visibility = View.GONE
                val errorMsg = getString(R.string.network_error)
                tvError.text = errorMsg
                tvError.visibility = View.VISIBLE
                Toast.makeText(requireContext(), "$errorMsg\n${e.message}", Toast.LENGTH_LONG)
                        .show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

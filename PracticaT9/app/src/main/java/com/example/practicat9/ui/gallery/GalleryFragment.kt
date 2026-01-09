package com.example.practicat9.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.practicat9.R
import com.example.practicat9.databinding.FragmentGalleryBinding
import com.example.practicat9.servicios.ClienteRetrofit
import kotlinx.coroutines.launch

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var etSpellId: EditText
    private lateinit var btnSearch: Button
    private lateinit var cardSpellDetails: CardView
    private lateinit var progressBar: ProgressBar
    private lateinit var tvName: TextView
    private lateinit var tvLevel: TextView
    private lateinit var tvSchool: TextView
    private lateinit var tvDescription: TextView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializar vistas
        etSpellId = binding.etSpellId
        btnSearch = binding.btnSearch
        cardSpellDetails = binding.cardSpellDetails
        progressBar = binding.progressBar
        tvName = binding.tvName
        tvLevel = binding.tvLevel
        tvSchool = binding.tvSchool
        tvDescription = binding.tvDescription

        // Configurar listener del botón
        btnSearch.setOnClickListener { searchSpellById() }
    }

    /**
     * Busca un hechizo por ID usando Coroutines
     * */
    private fun searchSpellById() {
        val idString = etSpellId.text.toString().trim()

        // Validar entrada
        if (idString.isEmpty()) {
            Toast.makeText(requireContext(), getString(R.string.enter_spell_id), Toast.LENGTH_SHORT)
                    .show()
            return
        }

        // Mostrar loading
        progressBar.visibility = View.VISIBLE
        cardSpellDetails.visibility = View.GONE

        // Lanzar coroutina
        lifecycleScope.launch {
            try {
                val response = ClienteRetrofit.apiService.getSpellById(idString)

                // Ocultar loading
                progressBar.visibility = View.GONE

                if (response.isSuccessful) {
                    val spell = response.body()
                    if (spell != null) {
                        // Mostrar detalles del hechizo
                        tvName.text = spell.name
                        tvLevel.text = spell.level.toString()
                        tvSchool.text = spell.school
                        tvDescription.text = spell.description
                        cardSpellDetails.visibility = View.VISIBLE
                    }
                } else {
                    // Error HTTP
                    val errorMsg =
                            when (response.code()) {
                                404 -> getString(R.string.spell_not_found)
                                500 -> getString(R.string.server_error)
                                else -> "${getString(R.string.unknown_error)} (${response.code()})"
                            }
                    Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                // Error de red
                progressBar.visibility = View.GONE
                val errorMsg = getString(R.string.network_error)
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

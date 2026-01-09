package com.example.practicat9.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.practicat9.R
import com.example.practicat9.databinding.FragmentSlideshowBinding
import com.example.practicat9.modelo.Spell
import com.example.practicat9.servicios.ClienteRetrofit
import kotlinx.coroutines.launch

class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var etSpellName: EditText
    private lateinit var etSpellLevel: EditText
    private lateinit var etSpellSchool: EditText
    private lateinit var etSpellDescription: EditText
    private lateinit var btnAddSpell: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializar vistas
        etSpellName = binding.etSpellName
        etSpellLevel = binding.etSpellLevel
        etSpellSchool = binding.etSpellSchool
        etSpellDescription = binding.etSpellDescription
        btnAddSpell = binding.btnAddSpell
        progressBar = binding.progressBar

        // Configurar listener del botón
        btnAddSpell.setOnClickListener { addNewSpell() }
    }

    private fun addNewSpell() {
        // Obtener valores de los campos
        val name = etSpellName.text.toString().trim()
        val levelString = etSpellLevel.text.toString().trim()
        val school = etSpellSchool.text.toString().trim()
        val description = etSpellDescription.text.toString().trim()

        // Validar campos
        if (name.isEmpty() || levelString.isEmpty() || school.isEmpty() || description.isEmpty()) {
            Toast.makeText(
                            requireContext(),
                            getString(R.string.fill_all_fields),
                            Toast.LENGTH_SHORT
                    )
                    .show()
            return
        }

        val level = levelString.toIntOrNull()
        if (level == null || level < 1 || level > 9) {
            Toast.makeText(
                            requireContext(),
                            "El nivel debe ser un número entre 1 y 9",
                            Toast.LENGTH_SHORT
                    )
                    .show()
            return
        }

        // Crear objeto Spell (id es null, el servidor lo genera)
        val newSpell =
                Spell(
                        id = null,
                        name = name,
                        level = level,
                        school = school,
                        description = description
                )

        // Mostrar loading
        progressBar.visibility = View.VISIBLE
        btnAddSpell.isEnabled = false

        // Lanzar coroutina
        lifecycleScope.launch {
            try {
                val response = ClienteRetrofit.apiService.addSpell(newSpell)

                // Ocultar loading
                progressBar.visibility = View.GONE
                btnAddSpell.isEnabled = true

                if (response.isSuccessful) {
                    val addedSpell = response.body()
                    Toast.makeText(
                                    requireContext(),
                                    getString(R.string.spell_added),
                                    Toast.LENGTH_LONG
                            )
                            .show()

                    // Limpiar formulario
                    clearForm()
                } else {
                    // Error HTTP
                    val errorMsg =
                            when (response.code()) {
                                500 -> getString(R.string.server_error)
                                else ->
                                        "${getString(R.string.error_adding_spell)} (${response.code()})"
                            }
                    Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                // Error de red
                progressBar.visibility = View.GONE
                btnAddSpell.isEnabled = true
                val errorMsg = getString(R.string.network_error)
                Toast.makeText(requireContext(), "$errorMsg\n${e.message}", Toast.LENGTH_LONG)
                        .show()
            }
        }
    }

    private fun clearForm() {
        etSpellName.text.clear()
        etSpellLevel.text.clear()
        etSpellSchool.text.clear()
        etSpellDescription.text.clear()
        etSpellName.requestFocus()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

package com.example.practicat9.ui.delete

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
import com.example.practicat9.databinding.FragmentDeleteSpellBinding
import com.example.practicat9.servicios.ClienteRetrofit
import kotlinx.coroutines.launch

/**
 * Fragmento para eliminar un hechizo del grimorio por ID DELETE http://10.0.2.2:3000/spells/{id}
 */
class DeleteSpellFragment : Fragment() {

    private var _binding: FragmentDeleteSpellBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var etSpellId: EditText
    private lateinit var btnDeleteSpell: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeleteSpellBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializar vistas
        etSpellId = binding.etSpellId
        btnDeleteSpell = binding.btnDeleteSpell
        progressBar = binding.progressBar

        // Configurar listener del botón
        btnDeleteSpell.setOnClickListener { deleteSpell() }
    }

    private fun deleteSpell() {
        val idString = etSpellId.text.toString().trim()

        // Validar entrada
        if (idString.isEmpty()) {
            Toast.makeText(requireContext(), getString(R.string.enter_spell_id), Toast.LENGTH_SHORT)
                    .show()
            return
        }

        // Mostrar loading
        progressBar.visibility = View.VISIBLE
        btnDeleteSpell.isEnabled = false

        // Lanzar coroutina
        lifecycleScope.launch {
            try {
                val response = ClienteRetrofit.apiService.deleteSpell(idString)

                // Ocultar loading
                progressBar.visibility = View.GONE
                btnDeleteSpell.isEnabled = true

                if (response.isSuccessful) {
                    // Hechizo eliminado exitosamente
                    Toast.makeText(
                                    requireContext(),
                                    getString(R.string.spell_deleted),
                                    Toast.LENGTH_LONG
                            )
                            .show()

                    etSpellId.text.clear()
                } else {
                    // Error HTTP
                    val errorMsg =
                            when (response.code()) {
                                404 -> getString(R.string.spell_not_found)
                                500 -> getString(R.string.server_error)
                                else ->
                                        "${getString(R.string.error_deleting_spell)} (${response.code()})"
                            }
                    Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                // Error de red
                progressBar.visibility = View.GONE
                btnDeleteSpell.isEnabled = true
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

package com.example.ejercicio10t6.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ejercicio10t6.databinding.FragmentPersonajeBinding
import com.example.ejercicio10t6.R

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragmentPersonaje : Fragment() {

    private lateinit var pageViewModel: PageViewModel
    private var _binding: FragmentPersonajeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPersonajeBinding.inflate(inflater, container, false)
        val root = binding.root

        val radioGroup: RadioGroup = root.findViewById(R.id.radioGroup)
        val imgButton: ImageButton = root.findViewById(R.id.imageButton)
        val editTextNombre: EditText = root.findViewById(R.id.editTextNombre)

        imgButton.setOnClickListener {
            val nombre = editTextNombre.text.toString().trim()

            if (nombre.isEmpty()) {
                Toast.makeText(requireContext(), R.string.empty_name, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedId = radioGroup.checkedRadioButtonId
            if (selectedId == -1) {
                Toast.makeText(requireContext(), getString(R.string.no_type), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val radioButton: RadioButton = root.findViewById(selectedId)
            val tipo = radioButton.text.toString()

            val mensaje = getString(R.string.reg_character, nombre, tipo)
            Toast.makeText(requireContext(), mensaje, Toast.LENGTH_SHORT).show()
        }

        return root
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(): PlaceholderFragmentPersonaje {
            return PlaceholderFragmentPersonaje()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
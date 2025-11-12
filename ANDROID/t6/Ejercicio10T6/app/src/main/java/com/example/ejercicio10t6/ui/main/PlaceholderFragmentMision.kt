package com.example.ejercicio10t6.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ejercicio10t6.databinding.FragmentMisionBinding
import com.example.ejercicio10t6.R


/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragmentMision : Fragment() {

    private lateinit var pageViewModel: PageViewModel
    private var _binding: FragmentMisionBinding? = null

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
        _binding = FragmentMisionBinding.inflate(inflater, container, false)
        val root = binding.root
        val toggleButton = binding.toggleButton
        val buttonChoose = binding.button2

        val checkGermany = binding.checkBox2
        val checkAfrica = binding.checkBox3
        val checkNorway = binding.checkBox4
        val checkFrance = binding.checkBox5

        val textViewStatus = TextView(requireContext())

        // ToggleButton listener
        toggleButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                textViewStatus.text = getString(R.string.on)
            } else {
                textViewStatus.text =getString(R.string.off)
            }
        }

        // Botón "Elegir" listener
        buttonChoose.setOnClickListener {
            val selectedMissions = mutableListOf<String>()
            if (checkGermany.isChecked) selectedMissions.add(getString(R.string.germany))
            if (checkAfrica.isChecked) selectedMissions.add(getString(R.string.africa))
            if (checkNorway.isChecked) selectedMissions.add(getString(R.string.norway))
            if (checkFrance.isChecked) selectedMissions.add(getString(R.string.france))

            if (selectedMissions.isEmpty()) {
                Toast.makeText(requireContext(), getString(R.string.must_choose_mission), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(),getString(R.string.chosen_missions, selectedMissions.joinToString(", ")),
                    Toast.LENGTH_SHORT).show()
            }

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
        fun newInstance(): PlaceholderFragmentMision {
            return PlaceholderFragmentMision()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
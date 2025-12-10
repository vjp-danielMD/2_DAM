package com.example.examenra2_danielmorenodominguez.ui.preferencias

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.examenra2_danielmorenodominguez.R
import com.example.examenra2_danielmorenodominguez.Sede

import com.example.examenra2_danielmorenodominguez.databinding.FragmentPreferenciasBinding

class PreferenciasFragment : Fragment() {

    private var _binding: FragmentPreferenciasBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var buttonGuardar: Button
    private lateinit var radioGroupEntrenadores: RadioGroup
    private lateinit var checkBoxCalentamiento: CheckBox
    private lateinit var checkBoxMusica: CheckBox
    private lateinit var textViewPreferencias: TextView



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val preferenciasViewModel =
//            ViewModelProvider(this).get(PreferenciasViewModel::class.java)

        _binding = FragmentPreferenciasBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textViewPreferencias
//        preferenciasViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }



        buttonGuardar = root.findViewById<Button>(R.id.buttonGuardar)
        radioGroupEntrenadores = root.findViewById<RadioGroup>(R.id.radioGroupEntrenadores)
        checkBoxCalentamiento = root.findViewById<CheckBox>(R.id.checkBoxCalentamiento)
        checkBoxMusica = root.findViewById<CheckBox>(R.id.checkBoxMusica)
        textViewPreferencias = root.findViewById<TextView>(R.id.textViewPreferencias)

        buttonGuardar.setOnClickListener {
            guardarPreferencias(radioGroupEntrenadores)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun guardarPreferencias(entrenadores: RadioGroup){
        if (checkBoxCalentamiento.isChecked) textViewPreferencias.text = resources.getString(R.string.calentamiento_si)
        if (checkBoxMusica.isChecked) textViewPreferencias.text = resources.getString(R.string.musica_si)
    }
}



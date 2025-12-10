package com.example.examenra2_danielmorenodominguez.ui.entrenamiento

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.examenra2_danielmorenodominguez.R
import com.example.examenra2_danielmorenodominguez.databinding.FragmentEntrenamientoBinding

class EntrenamientoFragment : Fragment() {

    private var _binding: FragmentEntrenamientoBinding? = null

    private lateinit var switchRecordatorio: Switch
    private lateinit var spinnerDeportes: Spinner

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val entrenamientoViewModel =
//            ViewModelProvider(this).get(EntrenamientoViewModel::class.java)

        _binding = FragmentEntrenamientoBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textView2
//        entrenamientoViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        switchRecordatorio = root.findViewById<Switch>(R.id.switchRecordar)
        spinnerDeportes = root.findViewById<Spinner>(R.id.spinnerDeportes)

        switchRecordatorio.setOnCheckedChangeListener { _, isChecked ->
            mostrarRecordatorio()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun mostrarRecordatorio(){
        if (switchRecordatorio.isChecked) Toast.makeText(
            context,
            resources.getString(R.string.recordatorio_true),
            Toast.LENGTH_SHORT).show()
        if (!switchRecordatorio.isChecked) Toast.makeText(
            context,
            resources.getString(R.string.recordatorio_false),
            Toast.LENGTH_SHORT).show()
        }
    }

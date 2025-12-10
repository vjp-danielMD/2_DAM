package com.example.examenra2_danielmorenodominguez.ui.contacto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.examenra2_danielmorenodominguez.R
import com.example.examenra2_danielmorenodominguez.Sede
import com.example.examenra2_danielmorenodominguez.databinding.FragmentContactoBinding

class ContactoFragment : Fragment() {

    private var _binding: FragmentContactoBinding? = null


    private lateinit var buttonBuscar: Button
    private lateinit var editTextSede: EditText



    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val contactoViewModel =
//            ViewModelProvider(this).get(ContactoViewModel::class.java)

        _binding = FragmentContactoBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textView2
//        contactoViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        var sedes: ArrayList<Sede> = arrayListOf(
            Sede("plasencia", 11111),
            Sede("badajoz", 22222),
            Sede("madrid", 33333),
            Sede("salamanca", 44444)
        )

        buttonBuscar = root.findViewById<Button>(R.id.buttonBuscar)
        editTextSede = root.findViewById<EditText>(R.id.editTextSede)

        buttonBuscar.setOnClickListener {
            buscarSede(sedes)
        }

        return root
    }

    fun buscarSede(sedes: ArrayList<Sede>){
        var sede: String = editTextSede.text.toString()
        if (sede.isEmpty()){
            Toast.makeText(context, resources.getString(R.string.porfavor_sede), Toast.LENGTH_SHORT).show()
        }
        if (sedes.isEmpty){
            Toast.makeText(context, resources.getString(R.string.no_hay_sedes), Toast.LENGTH_SHORT).show()
        }
        
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
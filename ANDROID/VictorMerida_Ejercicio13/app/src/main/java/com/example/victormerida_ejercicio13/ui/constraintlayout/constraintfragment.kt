package com.example.victormerida_ejercicio13.ui.constraintlayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.victormerida_ejercicio13.databinding.FragmentConstraintlayoutBinding

class constraintfragment : Fragment() {

    private var _binding: FragmentConstraintlayoutBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val constraintViewModel =
            ViewModelProvider(this).get(ConstraintViewModel::class.java)

        _binding = FragmentConstraintlayoutBinding.inflate(inflater, container, false)
        val root: View = binding.root



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
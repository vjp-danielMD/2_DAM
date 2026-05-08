package com.example.t10ej2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.t10ej2.databinding.ActivityListadoBinding
import kotlinx.coroutines.launch

class ListadoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListadoBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getDatabase(this)
        binding.rvArticulos.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            val lista = db.articuloDao().getAll()
            binding.rvArticulos.adapter = ArticuloAdapter(lista)
        }
    }
}

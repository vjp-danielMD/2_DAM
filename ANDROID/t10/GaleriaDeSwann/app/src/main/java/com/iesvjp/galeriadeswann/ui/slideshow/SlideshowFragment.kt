package com.iesvjp.galeriadeswann.ui.slideshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.iesvjp.galeriadeswann.R
import com.iesvjp.galeriadeswann.databinding.FragmentSlideshowBinding
import com.iesvjp.galeriadeswann.modelo.AppDatabase
import com.iesvjp.galeriadeswann.modelo.Articulo
import com.iesvjp.galeriadeswann.ui.ArticuloAdapter
import kotlinx.coroutines.launch

class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null
    private val binding get() = _binding!!
    private lateinit var adaptador: ArticuloAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)

        configurarRecyclerView()
        configurarSwipeToDelete()
        cargarArticulos()

        return binding.root
    }

    private fun configurarRecyclerView() {
        adaptador = ArticuloAdapter(emptyList()) { articulo ->
            mostrarBottomSheet(articulo)
        }
        binding.rvArticulos.layoutManager = LinearLayoutManager(requireContext())
        binding.rvArticulos.adapter = adaptador
    }

    private fun mostrarBottomSheet(articulo: Articulo) {
        val dialog = BottomSheetDialog(requireContext())
        val view = layoutInflater.inflate(R.layout.layout_bottom_sheet, null)
        dialog.setContentView(view)

        view.findViewById<android.widget.TextView>(R.id.tvTituloSheet).text = articulo.nombre

        view.findViewById<View>(R.id.btnSheetCompartir).setOnClickListener {
            dialog.dismiss()
            compartirArticulo(articulo)
        }

        view.findViewById<View>(R.id.btnSheetEditar).setOnClickListener {
            dialog.dismiss()
            mostrarDialogEditar(articulo)
        }

        dialog.show()
    }

    private fun mostrarDialogEditar(articulo: Articulo) {
        val builder = AlertDialog.Builder(requireContext())
        val viewInflated = layoutInflater.inflate(R.layout.dialog_editar, null)

        val etNombre = viewInflated.findViewById<EditText>(R.id.etDialogNombre)
        val etPrecio = viewInflated.findViewById<EditText>(R.id.etDialogPrecio)
        val etUnidades = viewInflated.findViewById<EditText>(R.id.etDialogUnidades)

        etNombre.setText(articulo.nombre)
        etPrecio.setText(articulo.precio.toString())
        etUnidades.setText(articulo.unidades.toString())

        builder.setView(viewInflated)
            .setPositiveButton("Guardar") { dialog, _ ->
                val nuevoNombre = etNombre.text.toString()
                val nuevoPrecio = etPrecio.text.toString()
                val nuevasUnidades = etUnidades.text.toString()

                if (nuevoNombre.isNotEmpty() && nuevoPrecio.isNotEmpty() && nuevasUnidades.isNotEmpty()) {
                    viewLifecycleOwner.lifecycleScope.launch {
                        val db = AppDatabase.getDatabase(requireContext())
                        val articuloEditado = Articulo(articulo.id, nuevoNombre, nuevoPrecio.toDouble(), nuevasUnidades.toInt())
                        db.articuloDao().actualizar(articuloEditado)
                        Toast.makeText(requireContext(), "Obra actualizada", Toast.LENGTH_SHORT).show()
                        cargarArticulos() // Recargar lista
                    }
                } else {
                    Toast.makeText(requireContext(), "Campos vacíos, no se guardó", Toast.LENGTH_SHORT).show()
                }
                dialog.dismiss()
            }
            .setNegativeButton("Cancelar") { dialog, _ -> dialog.cancel() }

        builder.show()
    }


    private fun compartirArticulo(articulo: Articulo) {
        val textoCompartir = """
            Estimado cliente, le presento una pieza de nuestra colección exclusiva:
            Obra: ${articulo.nombre}
            Valor de tasación: ${articulo.precio} €
            Disponibilidad: ${articulo.unidades} unidades disponibles.
            
            Atentamente, Galería de Charles Swann.
        """.trimIndent()

        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "Catálogo Exclusivo - Galería Swann")
            putExtra(Intent.EXTRA_TEXT, textoCompartir)
        }
        startActivity(Intent.createChooser(intent, "Compartir catálogo mediante:"))
    }

    private fun configurarSwipeToDelete() {
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(rc: RecyclerView, vh: RecyclerView.ViewHolder, t: RecyclerView.ViewHolder): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val posicion = viewHolder.adapterPosition
                val articuloAEliminar = adaptador.obtenerArticuloEnPosicion(posicion)

                viewLifecycleOwner.lifecycleScope.launch {
                    val db = AppDatabase.getDatabase(requireContext())
                    db.articuloDao().eliminar(articuloAEliminar)
                    cargarArticulos()


                    Snackbar.make(binding.root, "${articuloAEliminar.nombre} retirado de la colección", Snackbar.LENGTH_LONG)
                        .setAction("DESHACER") {
                            viewLifecycleOwner.lifecycleScope.launch {
                                db.articuloDao().insertar(articuloAEliminar)
                                cargarArticulos()
                            }
                        }.show()
                }
            }
        }
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(binding.rvArticulos)
    }

    private fun cargarArticulos() {
        viewLifecycleOwner.lifecycleScope.launch {
            val db = AppDatabase.getDatabase(requireContext())
            val lista = db.articuloDao().obtenerTodos()

            if (lista.isEmpty()) {
                binding.tvListaVacia.visibility = View.VISIBLE
                binding.rvArticulos.visibility = View.GONE
            } else {
                binding.tvListaVacia.visibility = View.GONE
                binding.rvArticulos.visibility = View.VISIBLE
                adaptador.actualizarDatos(lista)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
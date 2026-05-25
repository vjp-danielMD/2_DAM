package com.iesvjp.audioguiaturistica

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso

/**
 * Fragmento que muestra una galería o collage de los monumentos.
 * Utiliza la librería Picasso para cargar las imágenes de forma asíncrona
 * desde URLs externas, mostrando una imagen por defecto mientras se carga
 * y una imagen de error si falla la descarga.
 */
class MonumentosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflamos el layout asociado a este fragmento
        return inflater.inflate(R.layout.fragment_monumentos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Encontrar los ImageViews en la vista inflada para cada monumento
        val ivPlaza = view.findViewById<ImageView>(R.id.ivPlaza)
        val ivCatedral = view.findViewById<ImageView>(R.id.ivCatedral)
        val ivUniversidad = view.findViewById<ImageView>(R.id.ivUniversidad)

        // 2. Definir las URLs externas desde las cuales se descargarán las imágenes
        val urlUniversidad = "https://picsum.photos/id/1018/800/600"
        val urlPlaza = "https://picsum.photos/id/1019/800/600"
        val urlCatedral = "https://picsum.photos/id/1022/800/600"

        // 3. Cargar las imágenes usando Picasso, configurando placeholders y errores
        // Imagen de la Plaza Mayor
        Picasso.get()
            .load(urlPlaza)
            .placeholder(R.drawable.loading_placeholder) // Se muestra mientras carga
            .error(R.drawable.error_image)               // Se muestra si ocurre un error
            .into(ivPlaza)

        // Imagen de la Catedral
        Picasso.get()
            .load(urlCatedral)
            .placeholder(R.drawable.loading_placeholder)
            .error(R.drawable.error_image)
            .into(ivCatedral)

        // Imagen de la Universidad
        Picasso.get()
            .load(urlUniversidad)
            .placeholder(R.drawable.loading_placeholder)
            .error(R.drawable.error_image)
            .into(ivUniversidad)
    }
}
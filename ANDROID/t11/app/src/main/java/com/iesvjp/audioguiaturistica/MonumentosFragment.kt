package com.iesvjp.audioguiaturistica

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso

class MonumentosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_monumentos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Encontrar los ImageViews en la vista inflada
        val ivPlaza = view.findViewById<ImageView>(R.id.ivPlaza)
        val ivCatedral = view.findViewById<ImageView>(R.id.ivCatedral)
        val ivUniversidad = view.findViewById<ImageView>(R.id.ivUniversidad)

        // 2. URLs exactas y definitivas
        val urlUniversidad = "https://picsum.photos/id/1018/800/600"
        val urlPlaza = "https://picsum.photos/id/1019/800/600"
        val urlCatedral = "https://picsum.photos/id/1022/800/600"
        // 3. Cargar las imágenes usando Picasso
        Picasso.get()
            .load(urlPlaza)
            .placeholder(R.drawable.loading_placeholder)
            .error(R.drawable.error_image)
            .into(ivPlaza)

        Picasso.get()
            .load(urlCatedral)
            .placeholder(R.drawable.loading_placeholder)
            .error(R.drawable.error_image)
            .into(ivCatedral)

        Picasso.get()
            .load(urlUniversidad)
            .placeholder(R.drawable.loading_placeholder)
            .error(R.drawable.error_image)
            .into(ivUniversidad)
    }
}
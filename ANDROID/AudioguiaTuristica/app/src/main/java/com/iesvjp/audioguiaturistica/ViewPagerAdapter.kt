package com.iesvjp.audioguiaturistica

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Adaptador para gestionar los fragmentos que se muestran en el ViewPager2.
 * Permite cambiar entre el Fragmento de Monumentos y el Fragmento de Audioguía.
 */
class ViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    /**
     * Devuelve el número total de pestañas (tabs).
     */
    override fun getItemCount(): Int = 2

    /**
     * Crea y devuelve el Fragmento correspondiente según la posición seleccionada.
     */
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MonumentosFragment() // Tab 1: Galería de imágenes
            1 -> AudioguiaFragment()  // Tab 2: Reproductor de audioguía
            else -> MonumentosFragment()
        }
    }
}
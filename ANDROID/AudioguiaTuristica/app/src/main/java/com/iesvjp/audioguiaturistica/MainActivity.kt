package com.iesvjp.audioguiaturistica

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient

/**
 * Actividad principal que aloja el TabLayout y el ViewPager2.
 * Esta actividad permite la navegación entre el fragmento de los monumentos
 * y el fragmento de la audioguía.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        // Configuramos un cliente OkHttp personalizado para Picasso
        // Esto permite añadir un User-Agent a las peticiones HTTP y evitar posibles bloqueos de los servidores de imágenes
        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                    .build()
                chain.proceed(newRequest)
            }
            .build()

        // Creamos la instancia de Picasso utilizando el cliente OkHttp personalizado
        val picasso = Picasso.Builder(this)
            .downloader(OkHttp3Downloader(client))
            .build()

        // Establecemos nuestra instancia configurada como la instancia principal/singleton de Picasso
        Picasso.setSingletonInstance(picasso)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Enlazamos los componentes de la interfaz de usuario
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)

        // Configuramos el adaptador para el ViewPager2, pasándole esta actividad
        val adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter

        // Vinculamos el TabLayout con el ViewPager2 utilizando TabLayoutMediator
        // Esto permite que el título de cada pestaña se actualice según la posición actual
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.tab_monumentos)
                1 -> tab.text = getString(R.string.tab_audioguia)
            }
        }.attach()
    }
}
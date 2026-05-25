package com.iesvjp.audioguiaturistica

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso

/**
 * Fragmento que gestiona el reproductor de audioguía para los monumentos.
 * Permite iniciar, pausar, detener, adelantar o retroceder el audio, y cambiar entre pistas.
 * Utiliza la clase MediaPlayer y maneja la liberación de recursos en el ciclo de vida.
 */
class AudioguiaFragment : Fragment(R.layout.fragment_audioguia) {

    // Reproductor de audio único para toda la aplicación
    private var mediaPlayer: MediaPlayer? = null

    // Lista de recursos de audio locales (en res/raw)
    private val audios = listOf(
        R.raw.audio_catedral,
        R.raw.audio_plaza,
        R.raw.audio_universidad
    )

    // Lista de títulos correspondientes a cada audio
    private val titles = listOf(
        R.string.monumento_catedral,
        R.string.monumento_plaza,
        R.string.monumento_universidad
    )

    // Lista de imágenes relacionadas con el monumento actual
    private val images = listOf(
        "https://picsum.photos/id/1022/800/500",
        "https://picsum.photos/id/1019/800/500",
        "https://picsum.photos/id/1018/800/500"
    )

    // Índice de la pista de audio actual (0 por defecto)
    private var currentAudioIndex = 0

    // Declaramos los objetos de la interfaz
    private lateinit var ivMonument: ImageView
    private lateinit var tvTitle: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Enlazamos los componentes visuales del layout fragment_audioguia.xml
        ivMonument = view.findViewById(R.id.ivMonument)
        tvTitle = view.findViewById(R.id.tvTitle)

        // Inicialización de los botones de control del reproductor
        val btnPlay = view.findViewById<Button>(R.id.btnPlay)
        val btnPause = view.findViewById<Button>(R.id.btnPause)
        val btnStop = view.findViewById<Button>(R.id.btnStop)
        val btnForward = view.findViewById<Button>(R.id.btnForward)
        val btnBackward = view.findViewById<Button>(R.id.btnBackward)
        val btnNext = view.findViewById<Button>(R.id.btnNext)

        // 2. Forzamos la carga del primer monumento (Catedral) al abrir la pestaña
        updateUI()

        // 3. Lógica de botones
        btnPlay.setOnClickListener {
            if (mediaPlayer == null) {
                // Si el reproductor no existe o se ha detenido por completo, lo inicializamos
                playAudio()
            } else {
                // Si ya está creado, lo reanudamos
                mediaPlayer?.start()
            }
        }

        btnPause.setOnClickListener {
            // Pausar el audio si actualmente se está reproduciendo
            if (mediaPlayer?.isPlaying == true) {
                mediaPlayer?.pause()
            }
        }

        btnStop.setOnClickListener {
            // Detener y liberar el reproductor completamente
            stopAudio()
        }

        btnForward.setOnClickListener {
            // Adelantar 5 segundos
            mediaPlayer?.let {
                val newPosition = it.currentPosition + 5000
                if (newPosition < it.duration) it.seekTo(newPosition) else it.seekTo(it.duration)
            }
        }

        btnBackward.setOnClickListener {
            // Retroceder 5 segundos
            mediaPlayer?.let {
                val newPosition = it.currentPosition - 5000
                if (newPosition > 0) it.seekTo(newPosition) else it.seekTo(0)
            }
        }

        btnNext.setOnClickListener {
            // Cambiar a la siguiente pista circularmente
            currentAudioIndex = (currentAudioIndex + 1) % audios.size
            stopAudio() // Nos aseguramos de detener el actual antes de iniciar el siguiente
            playAudio()

            // 4. Al cambiar de pista, actualizamos la foto y el texto correspondientes
            updateUI()

            Toast.makeText(context, "Cambiando a pista ${currentAudioIndex + 1}", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Función encargada de actualizar la interfaz dinámicamente mediante Picasso y los recursos de string.
     */
    private fun updateUI() {
        // Cambia el título de la pantalla usando los recursos string
        tvTitle.text = getString(titles[currentAudioIndex])

        // Carga la imagen de forma dinámica usando Picasso y define los placeholders
        Picasso.get()
            .load(images[currentAudioIndex])
            .placeholder(R.drawable.loading_placeholder)
            .error(R.drawable.error_image)
            .into(ivMonument)
    }

    /**
     * Inicia la reproducción del audio actual, creando una nueva instancia de MediaPlayer.
     */
    private fun playAudio() {
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer.create(context, audios[currentAudioIndex])
        mediaPlayer?.start()

        // Detener automáticamente el reproductor cuando finalice la pista
        mediaPlayer?.setOnCompletionListener {
            stopAudio()
        }
    }

    /**
     * Detiene la reproducción y libera el recurso asociado.
     */
    private fun stopAudio() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    /**
     * Ciclo de vida: al destruir el fragmento, liberamos el MediaPlayer para ahorrar memoria.
     */
    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
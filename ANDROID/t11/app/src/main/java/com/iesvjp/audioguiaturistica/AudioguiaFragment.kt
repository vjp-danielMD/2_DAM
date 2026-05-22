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

class AudioguiaFragment : Fragment(R.layout.fragment_audioguia) {

    private var mediaPlayer: MediaPlayer? = null

    private val audios = listOf(
        R.raw.audio_catedral,
        R.raw.audio_plaza,
        R.raw.audio_universidad
    )

    private val titles = listOf(
        R.string.monumento_catedral,
        R.string.monumento_plaza,
        R.string.monumento_universidad
    )

    private val images = listOf(
        "https://picsum.photos/id/1022/800/500",
        "https://picsum.photos/id/1019/800/500",
        "https://picsum.photos/id/1018/800/500"
    )

    private var currentAudioIndex = 0

    // Declaramos los objetos de la interfaz
    private lateinit var ivMonument: ImageView
    private lateinit var tvTitle: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 2. Enlazamos los componentes visuales de tu fragment_audioguia.xml
        ivMonument = view.findViewById(R.id.ivMonument)
        tvTitle = view.findViewById(R.id.tvTitle)

        // Inicialización de botones
        val btnPlay = view.findViewById<Button>(R.id.btnPlay)
        val btnPause = view.findViewById<Button>(R.id.btnPause)
        val btnStop = view.findViewById<Button>(R.id.btnStop)
        val btnForward = view.findViewById<Button>(R.id.btnForward)
        val btnBackward = view.findViewById<Button>(R.id.btnBackward)
        val btnNext = view.findViewById<Button>(R.id.btnNext)

        // 3. Forzamos la carga del primer monumento (Catedral) al abrir la pestaña
        updateUI()

        // Lógica de botones
        btnPlay.setOnClickListener {
            if (mediaPlayer == null) {
                playAudio()
            } else {
                mediaPlayer?.start()
            }
        }

        btnPause.setOnClickListener {
            if (mediaPlayer?.isPlaying == true) {
                mediaPlayer?.pause()
            }
        }

        btnStop.setOnClickListener {
            stopAudio()
        }

        btnForward.setOnClickListener {
            mediaPlayer?.let {
                val newPosition = it.currentPosition + 5000
                if (newPosition < it.duration) it.seekTo(newPosition) else it.seekTo(it.duration)
            }
        }

        btnBackward.setOnClickListener {
            mediaPlayer?.let {
                val newPosition = it.currentPosition - 5000
                if (newPosition > 0) it.seekTo(newPosition) else it.seekTo(0)
            }
        }

        btnNext.setOnClickListener {
            currentAudioIndex = (currentAudioIndex + 1) % audios.size
            stopAudio()
            playAudio()

            // 4. ¡Sincronización! Al cambiar de pista, actualizamos la foto y el texto correspondientes
            updateUI()

            Toast.makeText(context, "Cambiando a pista ${currentAudioIndex + 1}", Toast.LENGTH_SHORT).show()
        }
    }

    // 5. Función encargada de actualizar la interfaz dinámicamente mediante Picasso
    private fun updateUI() {
        // Cambia el título de la pantalla usando los recursos string
        tvTitle.text = getString(titles[currentAudioIndex])

        // Carga la imagen de forma dinámica usando tus propios placeholders de drawable
        Picasso.get()
            .load(images[currentAudioIndex])
            .placeholder(R.drawable.loading_placeholder)
            .error(R.drawable.error_image)
            .into(ivMonument)
    }

    private fun playAudio() {
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer.create(context, audios[currentAudioIndex])
        mediaPlayer?.start()

        mediaPlayer?.setOnCompletionListener {
            stopAudio()
        }
    }

    private fun stopAudio() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
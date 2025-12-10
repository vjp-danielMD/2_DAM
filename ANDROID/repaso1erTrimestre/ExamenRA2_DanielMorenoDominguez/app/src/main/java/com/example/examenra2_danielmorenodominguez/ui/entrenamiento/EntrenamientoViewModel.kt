package com.example.examenra2_danielmorenodominguez.ui.entrenamiento

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EntrenamientoViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Elige un deporte"
    }
    val text: LiveData<String> = _text
}
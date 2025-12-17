package com.example.ejercicio01t9.servicios

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ClienteRetrofit {


        private val BASE_URL = "https://api.restful-api.dev/"

        // Crea un objeto Retrofit que se utilizará para realizar peticiones a la API
        private fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        // Crea un objeto de la interfaz APIService (interfaz que contiene las diferentes peticiones a la API)
        // a partir del objeto Retrofit.
        // Se añade "by lazy" para que este objeto se cree únicamente cuando se invoque (solo se crea cuando se vaya a utilizar, si se utiliza).
        val apiService: APIService by lazy {
            getRetrofitInstance().create(APIService::class.java)
        }
    }
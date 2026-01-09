package com.example.practicat9.servicios

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ClienteRetrofit {
    
    // URL base para el emulador Android (10.0.2.2 = localhost del host)
    private const val BASE_URL = "http://10.0.2.2:3000/"
    
    /**
     * Cliente OkHttp configurado (puede añadirse logging o interceptors aquí)
     */
    private val okHttpClient = OkHttpClient.Builder()
        .build()
    
    /**
     * Instancia de Retrofit con lazy initializatio
     */
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    
    /**
     * Instancia del servicio API
     *
     */
    val apiService: APIService by lazy {
        retrofit.create(APIService::class.java)
    }
}

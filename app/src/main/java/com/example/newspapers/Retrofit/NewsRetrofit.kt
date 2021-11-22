package com.example.newspapers.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsRetrofit {
    companion object{
        const val API_KEY = "09fa387710194a508488c7faed785352"
        private const val baseUrl = "https://newsapi.org/v2/"
        fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}
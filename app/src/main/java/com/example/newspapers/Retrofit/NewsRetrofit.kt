package com.example.newspapers.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsRetrofit {
    companion object{
        const val API_KEY = "6c954b8fab6947678c7263eb42263c06"
        private const val baseUrl = "https://newsapi.org/v2/"
        fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}
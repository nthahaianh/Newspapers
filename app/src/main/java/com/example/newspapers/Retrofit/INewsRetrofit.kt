package com.example.newspapers.Retrofit

import com.example.newspapers.Model.ResultNews
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface INewsRetrofit {
    @GET("everything")
    fun getEveryThing(
        @Query("q") query: String,
        @Query("sortBy") sortBy: String,
        @Query("apiKey") apiKey: String
    ): Call<ResultNews>

    @GET("top-headlines")
    fun getHeadlines(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") apiKey: String
    ): Call<ResultNews>
}
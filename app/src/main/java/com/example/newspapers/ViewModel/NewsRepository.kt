package com.example.newspapers.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.newspapers.Model.Article
import com.example.newspapers.Model.ResultNews
import com.example.newspapers.Retrofit.NewsRetrofit.Companion.API_KEY
import com.example.newspapers.Retrofit.INewsRetrofit
import com.example.newspapers.Retrofit.NewsRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepository {

    var iNewsRetrofit: INewsRetrofit = NewsRetrofit.getRetrofit().create(INewsRetrofit::class.java)
    fun getEverything(
        q: String,
        sortBy: String,
        resultLiveData: MutableLiveData<MutableList<Article>>
    ) {
        iNewsRetrofit.getEveryThing(q, sortBy, API_KEY).enqueue(object : Callback<ResultNews> {
            override fun onResponse(call: Call<ResultNews>, response: Response<ResultNews>) {
                if (response.isSuccessful) {
                    Log.e(
                        "myRepository",
                        "${response.body()?.status} - ${response.body()?.totalResults}"
                    )
                    var listNews: MutableList<Article>? = response.body()?.articles
                    if (listNews != null) {
                        Log.e("listNews", "${listNews?.size}")
                        resultLiveData.value = listNews
                    }
                }
            }

            override fun onFailure(call: Call<ResultNews>, t: Throwable) {
                Log.e("getEverything", "error")
            }

        })
    }

    fun getHeadlines(
        country: String,
        category: String,
        resultLiveData: MutableLiveData<MutableList<Article>>
    ) {
        iNewsRetrofit.getHeadlines(country, category, API_KEY)
            .enqueue(object : Callback<ResultNews> {
                override fun onResponse(call: Call<ResultNews>, response: Response<ResultNews>) {
                    if (response.isSuccessful) {
                        Log.e(
                            "myRepository",
                            "${response.body()?.status} - ${response.body()?.totalResults}"
                        )
                        var listNews: MutableList<Article>? = response.body()?.articles
                        if (listNews != null) {
                            Log.e("listNews", "${listNews?.size}")
                            resultLiveData.value = listNews
                        }
                    }
                }

                override fun onFailure(call: Call<ResultNews>, t: Throwable) {
                    Log.e("getHeadlines", "error")
                }
            })
    }
}
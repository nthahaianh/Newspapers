package com.example.newspapers.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newspapers.Model.Article

class NewsViewModel :ViewModel() {
    private val repository = NewsRepository()
    var listNews : MutableLiveData<MutableList<Article>> = MutableLiveData()
    var listSearch : MutableLiveData<MutableList<Article>> = MutableLiveData()
    var urlNews:MutableLiveData<String> = MutableLiveData()
    init {
        listNews.value = mutableListOf()
        listSearch.value = mutableListOf()
        urlNews.value = ""
    }

    fun getData(){
//        repository.getEverything("apple","2021-11-20","2021-11-20","publishedAt",listNews)
        repository.getHeadlines("us","business",listNews)
    }

    fun getListSearch(strSearch: String) {
        repository.getEverything(strSearch,"publishedAt",listNews)
    }
}
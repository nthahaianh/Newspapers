package com.example.newspapers.Model

data class ResultNews(
    val status: String,
    val totalResults: Int,
    val articles: MutableList<Article>
)
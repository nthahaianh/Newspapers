package com.example.newspapers.Model

import com.example.newspapers.Model.Article

data class ResultNews (
    val status: String,
    val totalResults: Int,
    val articles: MutableList<Article>
)
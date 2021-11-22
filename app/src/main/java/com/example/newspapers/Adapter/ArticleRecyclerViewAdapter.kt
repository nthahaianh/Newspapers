package com.example.newspapers.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newspapers.Model.Article
import com.example.newspapers.R
import java.text.SimpleDateFormat
import java.util.*

class ArticleRecyclerViewAdapter(var listNews: MutableList<Article>) :
    RecyclerView.Adapter<ArticleRecyclerViewAdapter.ViewHolder>() {
    lateinit var itemClick: (position: Int) -> Unit
    fun setCallBackItem(click: (position: Int) -> Unit) {
        itemClick = click
    }

    lateinit var view: View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        this.view = view
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listNews.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = listNews[position]
        holder.time.text = changeTimeFormat(article.publishedAt)
        try {
            Glide.with(view).load(article.urlToImage).into(holder.image)
        } catch (e: Exception) {
            e.stackTrace

        }
        holder.title.text = article.title
        holder.author.text = article.author
        holder.description.text = article.description
        holder.sourceName.text = article.source.name
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var time: TextView = view.findViewById(R.id.item_news_tvTime)
        var image: ImageView = view.findViewById(R.id.item_news_ivImage)
        var title: TextView = view.findViewById(R.id.item_news_tvTitle)
        var author: TextView = view.findViewById(R.id.item_news_tvAuthor)
        var description: TextView = view.findViewById(R.id.item_news_tvDescription)
        var sourceName: TextView = view.findViewById(R.id.item_news_tvSource)

        init {
            view.setOnClickListener {
                itemClick.invoke(adapterPosition)
            }
        }
    }

    fun changeTimeFormat(stringInput: String): String {
        var formatAPI = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        var date = formatAPI.parse(stringInput)
        var dateFormat = SimpleDateFormat("E MMM dd  HH:mm", Locale("en"))
        return dateFormat.format(date)
    }
}
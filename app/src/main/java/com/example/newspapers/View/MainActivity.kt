package com.example.newspapers.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newspapers.Adapter.ArticleRecyclerViewAdapter
import com.example.newspapers.R
import com.example.newspapers.ViewModel.NewsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var newsViewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        newsViewModel.getData()
        newsViewModel.listNews.observe(this, Observer {
            if (it == null || it.size <= 0) {
                main_pbLoad.visibility = View.VISIBLE
            } else {
                main_pbLoad.visibility = View.GONE
            }
        })
        setUp()
        setUpEditText()
    }

    private fun setUpEditText() {
        main_etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                newsViewModel.getListSearch(main_etSearch.text.toString())
                main_ivBack.visibility = View.VISIBLE
            }

        })
    }

    private fun setUp() {
        val layoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(baseContext, LinearLayoutManager.VERTICAL, false)
        var adapter = newsViewModel.listNews.value?.let { ArticleRecyclerViewAdapter(it) }
        adapter?.setCallBackItem {
            val urlNews = newsViewModel.listNews.value?.get(it)?.url
            val intent = Intent(this, WebActivity::class.java)
            intent.putExtra("url", urlNews)
            startActivity(intent)
        }
        main_rvNews.layoutManager = layoutManager
        main_rvNews.adapter = adapter
        newsViewModel.listNews.observe(this, Observer {
            adapter?.listNews = it
            adapter?.notifyDataSetChanged()
        })
        main_ivBack.setOnClickListener {
            newsViewModel.getData()
            main_etSearch.clearFocus()
            main_ivBack.visibility = View.GONE
        }
    }
}
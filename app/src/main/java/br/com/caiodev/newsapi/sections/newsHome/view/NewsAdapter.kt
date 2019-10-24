package br.com.caiodev.newsapi.sections.newsHome.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.caiodev.newsapi.R
import br.com.caiodev.newsapi.sections.newsHome.model.serializedModels.Article

class NewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var newsList: MutableList<Article>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
        return NewsViewHolder(
            itemView.inflate(
                R.layout.news_item_layout,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return getListSize()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(holder as NewsViewHolder) {

            newsList?.let {
                bind(it[position])
            }
        }
    }

    fun updateList(list: MutableList<Article>) {
        newsList = list
    }

    private fun getListSize(): Int {
        return newsList?.size ?: 0
    }
}
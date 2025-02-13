package br.com.caiodev.newsapi.sections.newsHome.view

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.caiodev.newsapi.R
import br.com.caiodev.newsapi.sections.newsHome.model.serializedModels.Article
import br.com.caiodev.newsapi.sections.utils.imageLoading.ImageLoader
import kotlinx.android.synthetic.main.news_item_layout.view.*

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    internal fun bind(model: Article) {

        model.urlToImage?.let {
            ImageLoader.loadImage(model.urlToImage, R.mipmap.ic_launcher, itemView.newsImage)
        } ?: run {
            itemView.newsImage.setImageDrawable(
                ContextCompat.getDrawable(
                    itemView.context,
                    R.mipmap.ic_launcher
                )
            )
        }

        model.title?.let { newsTitle ->
            itemView.newsTitle.text = newsTitle
        } ?: run {
            itemView.newsTitle.text = ""
        }
    }
}
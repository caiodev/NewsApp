package br.com.caiodev.newsapi.sections.newsHome.model.serializedModels

import kotlinx.serialization.Serializable

@Serializable
data class News(
    val status: String,
    val totalResults: Int,
    val articles: MutableList<Article>
)
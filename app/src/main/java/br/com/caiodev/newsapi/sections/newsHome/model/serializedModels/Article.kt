package br.com.caiodev.newsapi.sections.newsHome.model.serializedModels

import com.squareup.moshi.Json

data class Article(
    @field:Json(name = "source") val source: Source,
    @field:Json(name = "author") val author: String,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "description") val description: String,
    @field:Json(name = "url") val url: String,
    @field:Json(name = "urlToImage") val urlToImage: String,
    @field:Json(name = "publishedAt") val publishedAt: String,
    @field:Json(name = "content") val content: String
)
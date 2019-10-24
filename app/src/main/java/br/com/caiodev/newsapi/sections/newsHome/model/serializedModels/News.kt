package br.com.caiodev.newsapi.sections.newsHome.model.serializedModels

import com.squareup.moshi.Json

data class News (
	@field:Json(name = "status") val status : String,
	@field:Json(name = "totalResults") val totalResults : Int,
	@field:Json(name = "articles") val articles : MutableList<Article>
)
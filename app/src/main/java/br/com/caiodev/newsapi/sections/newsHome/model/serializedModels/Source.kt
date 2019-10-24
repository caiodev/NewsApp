package br.com.caiodev.newsapi.sections.newsHome.model.serializedModels

import com.squareup.moshi.Json

data class Source (
	@field:Json(name = "id") val id : String,
	@field:Json(name = "name") val name : String
)
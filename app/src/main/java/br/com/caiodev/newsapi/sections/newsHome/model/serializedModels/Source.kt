package br.com.caiodev.newsapi.sections.newsHome.model.serializedModels

import kotlinx.serialization.Serializable

@Serializable
data class Source(
    val id: String,
    val name: String
)
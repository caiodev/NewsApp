package br.com.caiodev.newsapi.sections.newsHome.model.repository

import br.com.caiodev.newsapi.sections.newsHome.model.callInterface.NewsInterface
import br.com.caiodev.newsapi.sections.utils.base.RemoteRepository

class NewsRepository(private val retrofitService: NewsInterface) : RemoteRepository() {

    suspend fun getTrendingNews(source: String, apiKey: String) = callApi(call = {
        retrofitService.getTrendingNewsAsync(source, apiKey)
    })
}
package br.com.caiodev.newsapi.sections.newsHome.model.repository

import br.com.caiodev.newsapi.sections.newsHome.model.callInterface.NewsInterface
import br.com.caiodev.newsapi.sections.utils.base.RemoteRepository
import br.com.caiodev.newsapi.sections.utils.factory.Retrofit

class NewsRepository : RemoteRepository() {

    private val retrofitService = Retrofit().getRetrofitService<NewsInterface>()

    suspend fun getTrendingNews(source: String, apiKey: String): Any {
        return callApi(call = {
            retrofitService.getTrendingNewsAsync(source, apiKey)
        })
    }
}
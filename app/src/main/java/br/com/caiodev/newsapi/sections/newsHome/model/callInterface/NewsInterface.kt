package br.com.caiodev.newsapi.sections.newsHome.model.callInterface

import br.com.caiodev.newsapi.sections.newsHome.model.serializedModels.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsInterface {

    @GET("top-headlines")
    suspend fun getTrendingNewsAsync(
        @Query("sources") source: String,
        @Query("apiKey") apiKey: String
    ): Response<News>
}
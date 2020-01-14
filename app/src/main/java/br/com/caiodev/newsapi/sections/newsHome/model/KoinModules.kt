package br.com.caiodev.newsapi.sections.newsHome.model

import br.com.caiodev.newsapi.sections.newsHome.model.repository.NewsRepository
import br.com.caiodev.newsapi.sections.newsHome.viewModel.NewsViewModel
import br.com.caiodev.newsapi.sections.utils.factory.Retrofit.getRetrofitService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val newsApiModule = module {
    viewModel { NewsViewModel(get()) }
    single { NewsRepository(getRetrofitService()) }
}
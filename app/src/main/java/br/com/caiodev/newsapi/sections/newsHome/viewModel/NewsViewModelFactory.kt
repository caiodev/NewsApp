package br.com.caiodev.newsapi.sections.newsHome.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.caiodev.newsapi.sections.newsHome.model.repository.NewsRepository

class NewsViewModelFactory(private val newsRepository: NewsRepository) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java))
            return NewsViewModel(newsRepository) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
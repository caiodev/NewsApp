package br.com.caiodev.newsapi.sections.newsHome.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.caiodev.newsapi.R
import br.com.caiodev.newsapi.sections.newsHome.model.repository.NewsRepository
import br.com.caiodev.newsapi.sections.newsHome.model.serializedModels.News
import br.com.caiodev.newsapi.sections.utils.base.SingleLiveEvent
import br.com.caiodev.newsapi.sections.utils.constants.Constants.clientSideError
import br.com.caiodev.newsapi.sections.utils.constants.Constants.forbidden
import br.com.caiodev.newsapi.sections.utils.constants.Constants.serverSideError
import br.com.caiodev.newsapi.sections.utils.constants.Constants.socketTimeoutException
import br.com.caiodev.newsapi.sections.utils.constants.Constants.sslHandshakeException
import br.com.caiodev.newsapi.sections.utils.constants.Constants.unknownHostException
import br.com.caiodev.newsapi.sections.utils.service.APICallResult
import kotlinx.coroutines.launch

class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    internal val successMutableLiveData = MutableLiveData<Any>()
    internal val errorSingleLiveEvent = SingleLiveEvent<Int>()
    internal var hasACallBeenMade = false
    internal var hasAnUnsuccessfulCallBeenMade = false

    fun getTrendingNews(source: String, apiKey: String) {
        viewModelScope.launch {

            when (val value = newsRepository.getTrendingNews(source, apiKey)) {
                is APICallResult.Success<*> -> {
                    with(value.data as News) {
                        successMutableLiveData.postValue(articles)
                    }
                }

                is APICallResult.Error<*> -> {
                    when (value.error) {
                        unknownHostException, socketTimeoutException -> errorSingleLiveEvent.postValue(
                            R.string.unknown_host_exception_and_socket_timeout_exception_message
                        )
                        sslHandshakeException -> errorSingleLiveEvent.postValue(R.string.ssl_handshake_exception_message)
                        clientSideError -> errorSingleLiveEvent.postValue(R.string.client_side_error_message)
                        serverSideError -> errorSingleLiveEvent.postValue(R.string.server_side_error_message)
                        forbidden -> errorSingleLiveEvent.postValue(R.string.api_query_limit_exceeded_error_message)
                        else -> errorSingleLiveEvent.postValue(R.string.generic_exception_and_generic_error_message)
                    }
                }
            }
        }
    }
}
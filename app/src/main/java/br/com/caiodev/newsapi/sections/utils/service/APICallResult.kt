package br.com.caiodev.newsapi.sections.utils.service

sealed class APICallResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : APICallResult<T>()
    data class Error<out T : Any>(val error: T) : APICallResult<T>()
}
package br.com.caiodev.newsapi.sections.utils.service

sealed class APICallResult<out T> {
    data class Success<out T>(val data: T) : APICallResult<T>()
    data class Error<out T>(val error: T) : APICallResult<T>()
}
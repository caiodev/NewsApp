package br.com.caiodev.newsapi.sections.utils.extensions

import androidx.lifecycle.ViewModel

@Suppress("UNUSED")
internal inline fun <reified T> ViewModel.castAttributeThroughViewModel(attribute: Any?) =
    attribute as T
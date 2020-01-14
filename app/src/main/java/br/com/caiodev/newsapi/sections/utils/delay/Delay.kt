package br.com.caiodev.newsapi.sections.utils.delay

import java.util.*
import kotlin.concurrent.schedule

object Delay {

    private val timer = Timer()

    fun delay(millis: Long, action: () -> Unit) {
        timer.schedule(millis) {
            action.invoke()
        }
    }
}
package br.com.caiodev.newsapi.sections.utils.init

import android.app.Application
import br.com.caiodev.newsapi.BuildConfig
import timber.log.Timber

@Suppress("UNUSED")
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}
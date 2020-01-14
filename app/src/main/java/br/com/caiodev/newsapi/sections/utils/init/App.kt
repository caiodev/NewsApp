package br.com.caiodev.newsapi.sections.utils.init

import android.app.Application
import br.com.caiodev.newsapi.BuildConfig
import br.com.caiodev.newsapi.sections.newsHome.model.newsApiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

@Suppress("UNUSED")
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(newsApiModule)
        }
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}
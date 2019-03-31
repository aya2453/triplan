package `fun`.triplan

import `fun`.triplan.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber
import timber.log.Timber.DebugTree

class TriplanApplication : DaggerApplication() {


    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }

    override fun applicationInjector(): AndroidInjector<out TriplanApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}
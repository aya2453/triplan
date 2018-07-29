package `fun`.triplan

import `fun`.triplan.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class TriplanApplication : DaggerApplication() {


    override fun onCreate() {
        super.onCreate()
    }

    override fun applicationInjector(): AndroidInjector<out TriplanApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}
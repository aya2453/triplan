package `fun`.triplan.di

import `fun`.triplan.TriplanApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    ActivityBuilder::class
])
interface AppComponent : AndroidInjector<TriplanApplication> {
    @Component.Builder
    interface Builder {
        fun build() : AppComponent
    }
}
package `fun`.triplan.di

import `fun`.triplan.TriplanApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    TriplanActivityBuilder::class
])
interface AppComponent : AndroidInjector<TriplanApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<TriplanApplication>()
}
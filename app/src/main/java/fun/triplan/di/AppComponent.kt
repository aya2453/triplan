package `fun`.triplan.di

import `fun`.triplan.TriplanApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivityBuilder::class
])
public interface AppComponent : AndroidInjector<TriplanApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<TriplanApplication>()
}
package `fun`.triplan.di

import `fun`.triplan.data.SessionRepository
import `fun`.triplan.data.TripRepository
import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

//@Module(includes = [AppModuleProvide::class])
@Module
abstract class AppModule {
    @Binds
    abstract fun provideContext(application: Application): Context

    @Module
    companion object {
        @Singleton
        @Provides
        @JvmStatic
        fun provideSessionRepository(): SessionRepository = SessionRepository()

        @Singleton
        @Provides
        @JvmStatic
        fun provideTripRepository(): TripRepository = TripRepository()
    }
}
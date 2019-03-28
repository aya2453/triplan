package `fun`.triplan.di

import `fun`.triplan.data.SessionRepository
import `fun`.triplan.data.TripRepository
import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class AppModule {
    @Binds
    abstract fun provideContext(application: Application): Context

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

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

        @Singleton
        @Provides
        @JvmStatic
        fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()
    }
}
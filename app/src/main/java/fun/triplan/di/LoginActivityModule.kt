package `fun`.triplan.di

import `fun`.triplan.data.UserRepository
import `fun`.triplan.ui.login.LoginActivity
import dagger.Module
import dagger.Provides

@Module
internal object LoginActivityModule {

    @Provides
    @JvmStatic
    fun provideLoginActivity(loginActivity: LoginActivity): LoginActivity {
        return loginActivity
    }


    @Provides
    @JvmStatic
    fun provideUserRepository(): UserRepository = UserRepository()

}
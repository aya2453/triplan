package `fun`.triplan.di

import `fun`.triplan.ui.login.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
interface ActivityBuilder {

    @ContributesAndroidInjector(modules = [LoginActivityModule::class])
    fun bindLoginActivity(): LoginActivity
}
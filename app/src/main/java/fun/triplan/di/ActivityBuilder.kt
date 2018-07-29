package `fun`.triplan.di

import `fun`.triplan.ui.login.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
internal abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [LoginActivityModule::class, ViewModelBuilder::class])
    internal abstract fun bindLoginActivity(): LoginActivity

}
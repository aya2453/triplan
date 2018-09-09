package `fun`.triplan.di

import `fun`.triplan.ui.login.LoginActivity
import `fun`.triplan.ui.trip.NewTripActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
internal abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [LoginActivityModule::class, LoginViewModelBuilder::class])
    internal abstract fun bindLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [NewTripActivityModule::class, NewTripViewModelBuilder::class])
    internal abstract fun bindNewTripActivity(): NewTripActivity

}
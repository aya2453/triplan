package `fun`.triplan.di

import `fun`.triplan.R
import `fun`.triplan.ui.TriplanActivity
import `fun`.triplan.ui.login.LoginFragmentBuilder
import `fun`.triplan.ui.trip.NewTripFragmentBuilder
import `fun`.triplan.ui.trip.TripListFragmentBuilder
import `fun`.triplan.ui.trip.UserFragmentBuilder
import androidx.navigation.Navigation
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector


@Module
internal abstract class TriplanActivityBuilder {

    @ContributesAndroidInjector(modules = [TriplanActivityModule::class])
    internal abstract fun bindTriplanActivity(): TriplanActivity
}

@Module(includes = [
    TripListFragmentBuilder::class,
    LoginFragmentBuilder::class,
    NewTripFragmentBuilder::class,
    UserFragmentBuilder::class])
abstract class TriplanActivityModule {
    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideNavController(activity: TriplanActivity) =
                Navigation.findNavController(activity, R.id.nav_host)
    }
}


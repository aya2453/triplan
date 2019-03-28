package `fun`.triplan.di

import `fun`.triplan.R
import `fun`.triplan.ui.TriplanActivity
import `fun`.triplan.ui.login.LoginFragmentBuilder
import `fun`.triplan.ui.trip.NewTripFragmentBuilder
import `fun`.triplan.ui.trip.UserFragmentBuilder
import `fun`.triplan.ui.triplist.TripListFragmentBuilder
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
    LoginFragmentBuilder::class,
    NewTripFragmentBuilder::class,
    TripListFragmentBuilder::class,
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


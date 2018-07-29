package `fun`.triplan.di

import `fun`.triplan.ui.login.LoginActivity
import android.app.Activity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap


@Module
public abstract class ActivityBuilder {
    @Binds
    @IntoMap
    @ActivityKey(LoginActivity::class)
    abstract fun bindLoginActivity(builder: LoginActivityComponent.Builder): AndroidInjector.Factory<out Activity>
}
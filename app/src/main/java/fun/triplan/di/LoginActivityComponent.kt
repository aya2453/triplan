package `fun`.triplan.di

import `fun`.triplan.ui.login.LoginActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [LoginActivityModule::class])
public interface LoginActivityComponent : AndroidInjector<LoginActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<LoginActivity>()
}
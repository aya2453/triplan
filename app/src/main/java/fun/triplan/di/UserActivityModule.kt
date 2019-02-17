package `fun`.triplan.di

import `fun`.triplan.R
import `fun`.triplan.data.SessionRepository
import `fun`.triplan.data.TripRepository
import `fun`.triplan.data.UserRepository
import `fun`.triplan.ui.login.LoginActivity
import `fun`.triplan.ui.login.LoginViewModel
import `fun`.triplan.ui.trip.NewTripViewModel
import `fun`.triplan.ui.trip.UserViewModel
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
internal object UserActivityModule {
    @Provides
    @JvmStatic
    fun provideUserRepository(): UserRepository = UserRepository()
}

@Module
internal abstract class UserViewModelBuilder {
    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    abstract fun bindUserViewModel(viewModel: UserViewModel): ViewModel
}
package `fun`.triplan.di

import `fun`.triplan.R
import `fun`.triplan.data.SessionRepository
import `fun`.triplan.data.TripRepository
import `fun`.triplan.ui.login.LoginActivity
import `fun`.triplan.ui.login.LoginViewModel
import `fun`.triplan.ui.trip.NewTripViewModel
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
internal object NewTripActivityModule {

    @Provides
    @JvmStatic
    fun provideTripRepository(): TripRepository = TripRepository()
}

@Module
internal abstract class NewTripViewModelBuilder {
    @Binds
    @IntoMap
    @ViewModelKey(NewTripViewModel::class)
    abstract fun bindNewTripViewModel(viewModel: NewTripViewModel): ViewModel
}



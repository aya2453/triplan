package `fun`.triplan.di

import `fun`.triplan.R
import `fun`.triplan.data.SessionRepository
import `fun`.triplan.ui.login.LoginActivity
import `fun`.triplan.ui.login.LoginViewModel
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
internal object LoginActivityModule {

    @Provides
    @JvmStatic
    fun provideGoogleSignInClient(loginActivity: LoginActivity): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder()
                .requestIdToken(loginActivity.getString(R.string.google_sign_in_server_client_id))
                .build()
        return GoogleSignIn.getClient(loginActivity, gso)
    }

    @Provides
    @JvmStatic
    fun provideSessionRepository(): SessionRepository = SessionRepository()


    @Provides
    @JvmStatic
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

}

@Module
internal abstract class ViewModelBuilder {
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginActivityViewModel(viewModel: LoginViewModel): ViewModel
}
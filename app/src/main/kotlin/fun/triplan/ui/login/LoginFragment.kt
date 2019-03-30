package `fun`.triplan.ui.login

import `fun`.triplan.R
import `fun`.triplan.di.ViewModelKey
import `fun`.triplan.ui.BaseFragment
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import kotlinx.android.synthetic.main.fragment_login.*
import timber.log.Timber
import javax.inject.Inject

class LoginFragment : BaseFragment() {
    @Inject
    lateinit var navController: NavController
    @Inject
    lateinit var googleSignInClient: GoogleSignInClient
    @Inject
    lateinit var firebaseAuth: FirebaseAuth
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    private val loginViewModel: LoginViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
    }

    private val gestureDetector by lazy {
        val simpleOnGestureListener = object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapUp(event: MotionEvent): Boolean {
                toggle(!systemUiVisible)
                return super.onSingleTapUp(event)
            }
        }
        GestureDetector(context, simpleOnGestureListener)
    }

    private var systemUiVisible: Boolean = false


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login_button.setOnClickListener {
            startActivityForResult(googleSignInClient.signInIntent, REQUEST_CODE_SIGN_IN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SIGN_IN) {
            handleSignIn(GoogleSignIn.getSignedInAccountFromIntent(data))
        }
    }

    /**
     * https://developers.google.com/android/reference/com/google/android/gms/common/api/CommonStatusCodes.html#INTERNAL_ERROR
     */
    private fun handleSignIn(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            account?.let {
                firebaseAuthWithGoogle(account)
            }
        } catch (e: ApiException) {
            Timber.d("エラー  ${e.statusCode} ")
        }
    }


    /**
     * https://developers-jp.googleblog.com/2016/10/firebase-task-2.html
     */
    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {
        val authCredential = GoogleAuthProvider.getCredential(account.idToken, null)
        firebaseAuth.signInWithCredential(authCredential).let { task ->
            task.addOnSuccessListener(activity!!) {
                navController.navigate(R.id.action_login_to_newTrip)
            }
            task.addOnFailureListener(activity!!) {
                it.cause.toString()
            }
        }
    }

    private fun handleFirebaseAuthSuccess(authResult: AuthResult) {
        authResult.user.getIdToken(false).let {
            if (it.isSuccessful) {
                Timber.d("#トークン ${it.result?.token.toString()}")
                loginViewModel.auth(it.result?.token!!)
            }
        }
    }

//    override fun onWindowFocusChanged(hasFocus: Boolean) {
//        super.onWindowFocusChanged(hasFocus)
//        if (hasFocus) {
//            toggle()
//        }
//    }
//
//    override fun onTouchEvent(event: MotionEvent?): Boolean {
//        gestureDetector.onTouchEvent(event)
//        return super.onTouchEvent(event)
//    }

    /**
     * システムUI(ステータスバー/ナビゲーションバー)の表示を切り替える
     *
     * @param visibility 表示=true 非表示=false デフォルト=false
     */
    private fun toggle(visibility: Boolean = false) {
        if (visibility) showSystemUi() else hideSystemUi()
        systemUiVisible = visibility

    }

    /**
     * システムUI(ステータスバー/ナビゲーションバー)を非表示にする
     */
    private fun hideSystemUi() {
        activity?.window?.decorView?.systemUiVisibility =
                View.SYSTEM_UI_FLAG_IMMERSIVE or
                        View.SYSTEM_UI_FLAG_LOW_PROFILE or
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                        View.SYSTEM_UI_FLAG_FULLSCREEN
    }

    /**
     * システムUI(ステータスバー/ナビゲーションバー)を表示する
     */
    private fun showSystemUi() {
        activity?.window?.decorView?.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }

    companion object {
        const val REQUEST_CODE_SIGN_IN: Int = 1;
    }
}

@Module
abstract class LoginFragmentBuilder {
    @ContributesAndroidInjector(modules = [LoginFragmentModule::class])
    abstract fun contributeLoginFragment(): LoginFragment
}

@Module
abstract class LoginFragmentModule {
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginFragmentViewModel(viewModel: LoginViewModel): ViewModel

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideGoogleSignInClient(fragment2: LoginFragment): GoogleSignInClient {
            val gso = GoogleSignInOptions.Builder()
                    .requestIdToken(fragment2.getString(R.string.google_sign_in_server_client_id))
                    .build()
            return GoogleSignIn.getClient(fragment2.context!!, gso)
        }
    }
}
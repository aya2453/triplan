package `fun`.triplan.ui.login

import `fun`.triplan.R
import `fun`.triplan.R.id.login_button
import `fun`.triplan.di.ViewModelFactory
import `fun`.triplan.ui.BaseActivity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View.*
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject


/**
 * ログイン画面
 */
class LoginActivity : BaseActivity() {

    @Inject
    lateinit var googleSignInClient: GoogleSignInClient
    @Inject
    lateinit var firebaseAuth: FirebaseAuth
    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    private val loginViewModel: LoginViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
    }

    private val gestureDetector by lazy {
        val simpleOnGestureListener = object : SimpleOnGestureListener() {
            override fun onSingleTapUp(event: MotionEvent): Boolean {
                toggle(!systemUiVisible)
                return super.onSingleTapUp(event)
            }
        }
        GestureDetector(this, simpleOnGestureListener)
    }

    private var systemUiVisible: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
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
            Log.d("エラー", e.statusCode.toString())
        }
    }


    /**
     * https://developers-jp.googleblog.com/2016/10/firebase-task-2.html
     */
    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {
        val authCredential = GoogleAuthProvider.getCredential(account.idToken, null)
        firebaseAuth.signInWithCredential(authCredential).let { task ->
            task.addOnSuccessListener(this) { it ->
                handleFirebaseAuthSuccess(it)
            }
            task.addOnFailureListener(this) {
                it.cause.toString()
            }
        }
    }

    private fun handleFirebaseAuthSuccess(authResult: AuthResult) {
        authResult.user.getIdToken(false).let {
            if (it.isSuccessful) {
                Log.d("#トークン", it.result?.token.toString())
                loginViewModel.auth(it.result?.token!!)
            }
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            toggle()
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        gestureDetector.onTouchEvent(event)
        return super.onTouchEvent(event)
    }

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
        window.decorView.systemUiVisibility =
                SYSTEM_UI_FLAG_IMMERSIVE or
                SYSTEM_UI_FLAG_LOW_PROFILE or
                SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                SYSTEM_UI_FLAG_LAYOUT_STABLE or
                SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                SYSTEM_UI_FLAG_FULLSCREEN
    }

    /**
     * システムUI(ステータスバー/ナビゲーションバー)を表示する
     */
    private fun showSystemUi() {
        window.decorView.systemUiVisibility =
                SYSTEM_UI_FLAG_LAYOUT_STABLE or
                SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }

    companion object {
        const val REQUEST_CODE_SIGN_IN: Int = 1;
    }
}
package `fun`.triplan.ui.login

import `fun`.triplan.R
import `fun`.triplan.data.UserRepository
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
import android.view.View.SYSTEM_UI_FLAG_IMMERSIVE
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
import android.view.View.SYSTEM_UI_FLAG_LOW_PROFILE
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject


/**
 * ログイン画面
 */
class LoginActivity : AppCompatActivity() {

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
    lateinit var googleSignInClient: GoogleSignInClient

    @Inject
    lateinit var userRepository: UserRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val gso = GoogleSignInOptions.Builder()
                .requestIdToken(getString(R.string.google_sign_in_server_client_id))
                .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
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

    private fun handleSignIn(completedTask: Task<GoogleSignInAccount>) {
        try {
            //TODO サーバー側での認証/登録/ログイン処理
        } catch (e: ApiException) {
            // TODO:ログ出す
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
package `fun`.triplan.presenter.login

import `fun`.triplan.R
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
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
}
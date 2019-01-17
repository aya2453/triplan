package `fun`.triplan.ui.trip

import `fun`.triplan.R
import `fun`.triplan.ui.login.LoginActivity
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_triplist.*

// TODO:DI設定
class TripListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_triplist)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            val user = FirebaseAuth.getInstance().currentUser
            if (user == null) {
                startActivityForResult(Intent(this, LoginActivity::class.java), AUTH_REQUEST_CODE)
            } else {
                launchNewTripActivity()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // 認証成功
        if (resultCode == Activity.RESULT_OK && requestCode == AUTH_REQUEST_CODE) {
            launchNewTripActivity()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun launchNewTripActivity() {
        startActivity(Intent(this, NewTripActivity::class.java))
    }

    companion object {
        private const val AUTH_REQUEST_CODE = 200
    }
}

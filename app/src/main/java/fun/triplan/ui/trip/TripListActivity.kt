package `fun`.triplan.ui.trip

import `fun`.triplan.R
import `fun`.triplan.ui.login.LoginActivity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_triplist.*

class TripListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_triplist)
        setSupportActionBar(toolbar)


        fab.setOnClickListener {
            startActivity(Intent(this, NewTripActivity::class.java))
        }
    }

}

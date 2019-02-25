package `fun`.triplan.ui.trip

import `fun`.triplan.R
import `fun`.triplan.ui.BaseFragment
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.android.ContributesAndroidInjector
import kotlinx.android.synthetic.main.fragment_triplist.*
import javax.inject.Inject


class TripListFragment : BaseFragment() {

    @Inject
    lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_triplist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fab.setOnClickListener {
            val user = FirebaseAuth.getInstance().currentUser
            if (user == null) {
//                startActivityForResult(Intent(context, LoginActivity::class.java), AUTH_REQUEST_CODE)
            } else {
                navController.navigate(R.id.loginFragment)
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
        // TODO:NewTripFragmentへの遷移
//        startActivity(Intent(context, NewTripActivity::class.java))
    }

    companion object {
        private const val AUTH_REQUEST_CODE = 200
    }
}

@Module
abstract class TripListFragmentBuilder {
    @ContributesAndroidInjector
    abstract fun contributeTripListFragment(): TripListFragment
}
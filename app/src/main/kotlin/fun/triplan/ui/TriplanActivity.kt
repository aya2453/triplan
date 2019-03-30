package `fun`.triplan.ui

import `fun`.triplan.R
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavController.OnDestinationChangedListener
import dagger.Lazy
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/**
 * SingleActivity
 */
class TriplanActivity : BaseActivity() {

    @Inject
    lateinit var navController: Lazy<NavController>

    // TODO:Databinding
    private val destinationChangedListener = OnDestinationChangedListener { _, destination, _ ->
        when (destination.id) {
            R.id.loginFragment -> appBarLayout.visibility = View.GONE
            else -> appBarLayout.visibility = View.VISIBLE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController.get().addOnDestinationChangedListener(destinationChangedListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        navController.get().removeOnDestinationChangedListener(destinationChangedListener)
    }

    override fun onBackPressed() {
        navController.get().let {
            when (navController.get().currentDestination?.id) {
                R.id.newTripFragment -> {
                    // Remove LoginFragment from back stack to prevent from returning to it when pressing Back or Up key.
                    // refs.https://developer.android.com/guide/navigation/navigation-getting-started#pop
                    it.navigate(R.id.action_newTrip_to_tripList)
                }
                else -> {
                    super.onBackPressed()
                }
            }
        }
    }
}

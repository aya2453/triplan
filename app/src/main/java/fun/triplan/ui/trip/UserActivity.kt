package `fun`.triplan.ui.trip

import `fun`.triplan.R
import `fun`.triplan.di.AppModule
import `fun`.triplan.di.UserActivityModule
import `fun`.triplan.di.ViewModelFactory
import `fun`.triplan.ui.BaseActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_triplist.*
import javax.inject.Inject

class UserActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(UserViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.container, UserFragment())
                    .commit()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchUserDetail("accountId")
    }
}
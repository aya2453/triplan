package `fun`.triplan.ui.trip

import `fun`.triplan.R
import `fun`.triplan.R.id.new_trip_edittext_start_date
import `fun`.triplan.R.id.new_trip_edittext_title_inner
import `fun`.triplan.R.id.submit_button
import `fun`.triplan.R.id.validation_form
import `fun`.triplan.databinding.ActivityNewTripBinding
import `fun`.triplan.di.ViewModelFactory
import `fun`.triplan.ui.BaseActivity
import `fun`.triplan.ui.common.afterTextChanged
import `fun`.triplan.ui.login.LoginActivity
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_new_trip.*
import javax.inject.Inject


class NewTripActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(NewTripViewModel::class.java)
        val binding = DataBindingUtil.setContentView<ActivityNewTripBinding>(this, R.layout.activity_new_trip)
        binding.viewModel = viewModel

        listOf<EditText>(new_trip_edittext_title_inner,
                new_trip_edittext_start_date, new_trip_edittext_start_date).forEach { editText ->
            editText.afterTextChanged {
                viewModel.isValid.set(validation_form.valid())
            }
        }

        submit_button.setOnClickListener {
            // Firebase RealTime Databaseに連携
            //            viewModel.register(new_trip_edittext_email_inner.toString(), new_trip_edittext_password_inner.toString())
        }
    }

}
package `fun`.triplan.ui.trip

import `fun`.triplan.R
import `fun`.triplan.databinding.ActivityNewTripBinding
import `fun`.triplan.di.ViewModelFactory
import `fun`.triplan.ui.BaseActivity
import `fun`.triplan.ui.common.afterTextChanged
import android.os.Bundle
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
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

        listOf<EditText>(new_trip_edittext_title_inner).forEach { editText ->
            editText.afterTextChanged {
                viewModel.isValid.set(validation_form.valid())
            }
        }

        submit_button.setOnClickListener {
            viewModel.requestNewTrip(
                    viewModel.trip.apply {
                        title = new_trip_edittext_title_inner.toString()
                    }
            )
        }
    }

}
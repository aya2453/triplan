package `fun`.triplan.ui.trip

import `fun`.triplan.R
import `fun`.triplan.databinding.ActivityNewTripBinding
import `fun`.triplan.di.ViewModelFactory
import `fun`.triplan.di.ViewModelKey
import `fun`.triplan.ui.common.afterTextChanged
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.DaggerFragment
import dagger.multibindings.IntoMap
import kotlinx.android.synthetic.main.activity_new_trip.*
import javax.inject.Inject

class NewTripFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(NewTripViewModel::class.java)
        val binding = DataBindingUtil.inflate<ActivityNewTripBinding>(inflater, R.layout.activity_new_trip, container, false)
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
        return binding.root
    }
}

@Module
abstract class NewTripFragmentBuilder {
    @ContributesAndroidInjector(modules = [NewTripFragmentModule::class])
    abstract fun contributeNewTripFragment(): NewTripFragment
}

@Module
abstract class NewTripFragmentModule {
    @Binds
    @IntoMap
    @ViewModelKey(NewTripViewModel::class)
    abstract fun bindNewTripFragmentViewModel(viewModel: NewTripViewModel): ViewModel
}
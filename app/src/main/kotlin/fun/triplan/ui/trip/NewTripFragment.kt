package `fun`.triplan.ui.trip

import `fun`.triplan.R
import `fun`.triplan.databinding.FragmentNewTripBinding
import `fun`.triplan.di.ViewModelKey
import `fun`.triplan.ui.common.afterTextChanged
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.DaggerFragment
import dagger.multibindings.IntoMap
import kotlinx.android.synthetic.main.fragment_new_trip.*
import javax.inject.Inject

class NewTripFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val newTripViewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(NewTripViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentNewTripBinding>(inflater, R.layout.fragment_new_trip, container, false)
        binding.viewModel = newTripViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listOf<EditText>(new_trip_edittext_title_inner,
                new_trip_edittext_start_date, new_trip_edittext_start_date).forEach { editText ->
            editText.afterTextChanged {
                newTripViewModel.isValid.set(validation_form.valid())
            }
        }

        submit_button.setOnClickListener {
            // Firebase RealTime Databaseに連携
            //            viewModel.register(new_trip_edittext_email_inner.toString(), new_trip_edittext_password_inner.toString())
        }
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
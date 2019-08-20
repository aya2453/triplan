package `fun`.triplan.ui.trip

import `fun`.triplan.R
import `fun`.triplan.databinding.FragmentNewTripBinding
import `fun`.triplan.di.ViewModelKey
import `fun`.triplan.ui.BaseFragment
import `fun`.triplan.ui.common.afterTextChanged
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.DaggerDialogFragment
import dagger.multibindings.IntoMap
import kotlinx.android.synthetic.main.fragment_new_trip.*
import java.util.Calendar.*
import javax.inject.Inject


class NewTripFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var navController: NavController

    private val newTripViewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(NewTripViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                navController.popBackStack(`fun`.triplan.R.id.triplanListFragment, false)
            }

        })

        newTripViewModel.initStartDate()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentNewTripBinding>(inflater, R.layout.fragment_new_trip, container, false)
        binding.viewModel = newTripViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listOf<EditText>(new_trip_edittext_title_inner,
                new_trip_edittext_start_date, new_trip_edittext_end_date).forEach { editText ->
            editText.afterTextChanged {
                newTripViewModel.isValid.set(validation_form.valid())
            }
        }

        new_trip_edittext_start_date.setOnClickListener {
            DatePickerDialogFragment.newInstance(true, this).show(fragmentManager!!, "datePicker")
        }

        new_trip_edittext_end_date.setOnClickListener {
            DatePickerDialogFragment.newInstance(false, this).show(fragmentManager!!, "datePicker")
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

//class DatePickerFragment : DialogFragment() {
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        val view = inflater.inflate(R.layout.date_picker, container)
//        val min = Calendar.getInstance().time
//        val max = Calendar.getInstance().apply {
//            add(Calendar.YEAR, 5)
//        }.time
//        view.findViewById<CalendarPickerView>(R.id.calendar_view).init(min, max)
//                .inMode(CalendarPickerView.SelectionMode.RANGE)
//        return view
//    }
//}


class DatePickerDialogFragment : DaggerDialogFragment(), DatePickerDialog.OnDateSetListener {

    private val viewModel by lazy { ViewModelProviders.of(targetFragment!!, viewModelFactory).get(NewTripViewModel::class.java) }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val isStartDate by lazy { arguments!!.getBoolean("isStartDate", false) }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val initDateInMills = intDateInMills()
        val (year, month, day) = convertToTriple(initDateInMills)
        val dialog = DatePickerDialog(context!!, this, year, month, day)
        if (!isStartDate) {
            // 終了日は開始日以前を指定できないようにする
            dialog.datePicker.minDate = viewModel.startDateInMills.value!!
        }
        return dialog
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val c = getInstance().apply {
            set(year, month, dayOfMonth)
        }
        if (isStartDate) {
            viewModel.startDateInMills.call(c.timeInMillis)
        } else {
            viewModel.endDateInMills.call(c.timeInMillis)
        }
        dialog?.dismiss()
    }

    companion object {
        @JvmStatic
        fun newInstance(isStartDate: Boolean, targetFragment: Fragment): DatePickerDialogFragment {
            return DatePickerDialogFragment().apply {
                arguments = bundleOf(Pair("isStartDate", isStartDate))
                setTargetFragment(targetFragment, 111)
            }
        }
    }

    private fun intDateInMills(): Long {
        return if (isStartDate) {
            viewModel.startDateInMills.value!!
        } else {
            viewModel.endDateInMills.value ?: viewModel.startDateInMills.value!!
        }
    }

    private fun convertToTriple(dateInMills: Long): Triple<Int, Int, Int> {
        val c = getInstance().apply {
            timeInMillis = dateInMills
        }
        return Triple(c.get(YEAR), c.get(MONTH), c.get(DAY_OF_MONTH))
    }
}

@Module
abstract class DatePickerDialogFragmentBuilder {
    @ContributesAndroidInjector(modules = [DatePickerDialogFragmentModule::class])
    abstract fun contributeDatePickerDialogFragment(): DatePickerDialogFragment
}

@Module
abstract class DatePickerDialogFragmentModule {
    @Binds
    @IntoMap
    @ViewModelKey(NewTripViewModel::class)
    abstract fun bindNewTripFragmentViewModel(viewModel: NewTripViewModel): ViewModel
}

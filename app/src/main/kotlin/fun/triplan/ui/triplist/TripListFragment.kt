package `fun`.triplan.ui.triplist

import `fun`.triplan.R
import `fun`.triplan.databinding.FragmentTriplistBinding
import `fun`.triplan.di.ViewModelKey
import `fun`.triplan.ui.BaseFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import timber.log.Timber
import javax.inject.Inject


class TripListFragment : BaseFragment() {

    @Inject
    lateinit var navController: NavController
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var binding: FragmentTriplistBinding

    private val tripListViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(TripListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        if (::binding.isInitialized) {
            return binding.root
        }
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_triplist, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = tripListViewModel

        val tripListAdapter = TripListAdapter()
        tripListAdapter.onTripClicked = { _, tripListId: Int ->
            // TODO:画面遷移のコード書く
            Timber.d("clicked! ${tripListId}")
        }
        binding.listRecyclerView.adapter = tripListAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tripListViewModel.newTripClickedEvent.observe(viewLifecycleOwner, "clickedTag", Observer { haveSignedIn ->
            if (haveSignedIn) {
                navController.navigate(R.id.action_triList_to_newTrip)
            } else {
                navController.navigate(R.id.action_triList_to_login)
            }
        })
        binding.fab.setOnClickListener {
            tripListViewModel.pushLoginOrNewTrip()
        }
    }

    private fun launchNewTripActivity() {
        // TODO:NewTripFragmentへの遷移
//        startActivity(Intent(context, NewTripActivity::class.java))
    }
}

@Module
abstract class TripListFragmentBuilder {
    @ContributesAndroidInjector(modules = [TripListViewModelModule::class])
    abstract fun contributeTripListFragment(): TripListFragment
}

@Module
abstract class TripListViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(TripListViewModel::class)
    abstract fun bindTripListViewModel(viewModel: TripListViewModel): ViewModel
}
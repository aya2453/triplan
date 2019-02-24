package `fun`.triplan.ui.trip

import `fun`.triplan.R
import `fun`.triplan.databinding.FragmentUserBinding
import `fun`.triplan.di.ViewModelKey
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

class UserFragment : Fragment() {

    private val viewModel by lazy { ViewModelProviders.of(this).get(UserViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentUserBinding>(inflater, R.layout.fragment_user, container, false).apply {
            viewModel = this@UserFragment.viewModel
            lifecycleOwner = this@UserFragment
        }
        return binding.root
    }
}

@Module
abstract class UserFragmentBuilder {
    @ContributesAndroidInjector(modules = [UserFragmentModule::class])
    abstract fun contributeUserFragment(): UserFragment
}
@Module
abstract class UserFragmentModule {
    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    abstract fun bindUserFragmentViewModel(viewModel: UserViewModel): ViewModel
}

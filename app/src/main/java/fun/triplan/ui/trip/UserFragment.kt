package `fun`.triplan.ui.trip

import `fun`.triplan.R
import `fun`.triplan.databinding.FragmentUserBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

class UserFragment : Fragment() {

    private val viewModel by lazy { ViewModelProviders.of(this).get(UserViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentUserBinding>(inflater, R.layout.fragment_user, container, false).apply {
            viewModel = this@UserFragment.viewModel
            setLifecycleOwner(this@UserFragment)
        }
        return binding.root
    }
}
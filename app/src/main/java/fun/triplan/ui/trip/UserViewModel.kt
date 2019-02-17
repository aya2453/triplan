package `fun`.triplan.ui.trip

import `fun`.triplan.data.TripRepository
import `fun`.triplan.data.UserRepository
import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class UserViewModel @Inject constructor(
        private val userRepository: UserRepository) : ViewModel() {


    fun fetchUserDetail(accountId : String) {
    }

}
package `fun`.triplan.ui.login

import `fun`.triplan.data.UserRepository
import android.arch.lifecycle.ViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor(
        private val userRepository: UserRepository) : ViewModel() {

    /**
     * idToken
     */
    fun auth(idToken: String) {
        userRepository.print(idToken)
    }
}
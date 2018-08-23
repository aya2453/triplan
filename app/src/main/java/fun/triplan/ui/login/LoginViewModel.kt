package `fun`.triplan.ui.login

import `fun`.triplan.data.SessionRepository
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor(
        private val sessionRepository: SessionRepository) : ViewModel() {

    /**
     * idToken
     */
    fun auth(idToken: String) {
        sessionRepository.print(idToken)
    }
}
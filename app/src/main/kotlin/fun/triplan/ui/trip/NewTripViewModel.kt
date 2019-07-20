package `fun`.triplan.ui.trip

import `fun`.triplan.data.TripRepository
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class NewTripViewModel @Inject constructor(
        private val tripRepository: TripRepository) : ViewModel() {

    var isValid: ObservableBoolean = ObservableBoolean(false)



    fun register(email: String, password: String) {
        // リクエスト
    }

}
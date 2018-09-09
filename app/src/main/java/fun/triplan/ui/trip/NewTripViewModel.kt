package `fun`.triplan.ui.trip

import `fun`.triplan.data.TripRepository
import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class NewTripViewModel @Inject constructor(
        private val tripRepository: TripRepository) : ViewModel() {

    var isValid: ObservableBoolean = ObservableBoolean(false)
    var trip: Trip = Trip()


    fun requestNewTrip(trip: Trip) {
        Log.d("#", "リクエスト")
    }

    data class Trip(var title: String = "")

}
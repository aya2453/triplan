package `fun`.triplan.ui.triplist

import `fun`.triplan.ui.common.LiveEvent
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class TripListViewModel @Inject constructor(
        private val firebaseAuth: FirebaseAuth) : ViewModel() {

    val newTripClickedEvent by lazy { LiveEvent<Boolean>() }

    fun pushLoginOrNewTrip() {
        newTripClickedEvent.call(firebaseAuth.currentUser != null)
    }
}
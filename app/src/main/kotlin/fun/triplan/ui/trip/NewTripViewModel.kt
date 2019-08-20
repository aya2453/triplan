package `fun`.triplan.ui.trip

import `fun`.triplan.data.TripRepository
import `fun`.triplan.ui.common.LiveEvent
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import java.util.*
import javax.inject.Inject

class NewTripViewModel @Inject constructor(
        private val tripRepository: TripRepository) : ViewModel() {

    var isValid: ObservableBoolean = ObservableBoolean(false)
    val startDateInMills by lazy { LiveEvent<Long>() }
    val endDateInMills by lazy { LiveEvent<Long>() }

    fun initStartDate() {
        // 現在日でデフォルト値セット
        val c = Calendar.getInstance()
        startDateInMills.call(Calendar.getInstance().timeInMillis)
    }

//    fun tripleToString(
//            value: Triple<Int, Int, Int>?
//    ): String {
//        return if (value == null) {
//            ""
//        } else {
//            "${value.first}年${value.second}月${value.third}日"
//        }
//    }

    fun register(email: String, password: String) {
        // リクエスト
    }
}

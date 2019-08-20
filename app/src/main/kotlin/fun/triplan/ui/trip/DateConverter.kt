package `fun`.triplan.ui.trip

import androidx.databinding.InverseMethod

abstract class DateConverter {
    companion object {
        @JvmStatic
        fun stringToLong(
                value: String
        ): Long {
            return 0L
//        return if (value.isNullOrBlank()) {
//            0L
//        } else {
//            val cal = getInstance()
//            val sdf = SimpleDateFormat("yyyy年MM月dd日", Locale.JAPAN)
//            cal.time = sdf.parse(value)
//            cal.timeInMillis
//        }
        }

        @InverseMethod("stringToLong")
        @JvmStatic
        fun longToString(
                value: Long
        ): String {
            return ""
//        return if (value == 0L) {
//            ""
//        } else {
//            val cal = getInstance().apply {
//                timeInMillis = value
//            }
//            "${cal.get(YEAR)}年${cal.get(MONTH) + 1}月${DAY_OF_MONTH}"
//        }
        }
    }


}
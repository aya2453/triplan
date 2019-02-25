package `fun`.triplan.data

import android.util.Log
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class TripRepository {

    fun print(idToken: String) {
        // TODO:API
        Log.d("#", "作成")
        val moshi = Moshi.Builder().also {
            val moshi = it.build()
        }.build()

       Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi))
               .baseUrl("")
               .build()
    }
}
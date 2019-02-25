package `fun`.triplan.data.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(val name: String, val id: Int)
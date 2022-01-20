package com.github.welblade.soccermatchsim.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Match(
    @Json(name = "descricao")
    val description: String,
    @Json(name = "local")
    val place: Place,
    @Json(name = "mandante")
    val home: Team,
    @Json(name = "visitante")
    val away: Team,
) : Parcelable
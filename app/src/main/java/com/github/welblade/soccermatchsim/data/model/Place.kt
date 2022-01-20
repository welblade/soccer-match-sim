package com.github.welblade.soccermatchsim.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Place(
    @Json(name = "nome")
    val name: String,
    @Json(name = "imagem")
    val image: String,
) : Parcelable
package com.github.welblade.soccermatchsim.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Place(
    @Json(name = "nome")
    val name: String,
    @Json(name = "imagem")
    val image: String,
)
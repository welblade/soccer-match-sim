package com.github.welblade.soccermatchsim.data.model

import com.squareup.moshi.Json

data class Place(
    @Json(name = "nome")
    val name: String,
    @field:Json(name = "imagem")
    val image: String,
)
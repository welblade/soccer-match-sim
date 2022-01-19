package com.github.welblade.soccermatchsim.data.model

import com.squareup.moshi.Json

data class Match(
    @Json(name = "descricao")
    val description: String,
    @Json(name = "local")
    val place: Place,
    @Json(name = "mandante")
    val home: Team,
    @Json(name = "visitante")
    val away: Team,
)
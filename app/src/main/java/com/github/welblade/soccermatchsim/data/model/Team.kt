package com.github.welblade.soccermatchsim.data.model

import com.squareup.moshi.Json

data class Team(
    @field:Json(name = "nome")
    val name: String,
    @field:Json(name = "forca")
    val stars: Int,
    @field:Json(name = "imagem")
    val image: String,
)

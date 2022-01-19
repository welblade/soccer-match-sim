package com.github.welblade.soccermatchsim.data.api

import com.github.welblade.soccermatchsim.data.model.Match
import retrofit2.http.GET

interface Matches {
    @GET("matches.json")
    suspend fun getMatches():List<Match>
}
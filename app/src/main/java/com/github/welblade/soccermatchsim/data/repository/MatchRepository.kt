package com.github.welblade.soccermatchsim.data.repository

import com.github.welblade.soccermatchsim.data.model.Match
import kotlinx.coroutines.flow.Flow

interface MatchRepository {
    suspend fun getMatches(): Flow<List<Match>>
}
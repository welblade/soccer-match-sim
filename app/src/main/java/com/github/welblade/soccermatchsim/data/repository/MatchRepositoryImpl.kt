package com.github.welblade.soccermatchsim.data.repository

import com.github.welblade.soccermatchsim.data.api.MatchesApi
import com.github.welblade.soccermatchsim.data.api.MatchesApiException
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class MatchRepositoryImpl(
    private val service: MatchesApi
) : MatchRepository {
    override suspend fun getMatches() = flow {
        try {
            val matches = service.getMatches()
            emit(matches)
        } catch (exception: HttpException) {
            throw MatchesApiException(exception.message())
        }
    }
}
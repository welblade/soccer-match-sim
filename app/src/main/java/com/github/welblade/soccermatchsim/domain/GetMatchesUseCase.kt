package com.github.welblade.soccermatchsim.domain

import com.github.welblade.soccermatchsim.data.model.Match
import com.github.welblade.soccermatchsim.data.repository.MatchRepository
import com.github.welblade.soccermatchsim.domain.base.UseCase
import kotlinx.coroutines.flow.Flow

class GetMatchesUseCase(
    private val repository: MatchRepository
) : UseCase.NoParam<List<Match>>() {
    override suspend fun execute(): Flow<List<Match>> {
        return repository.getMatches()
    }
}
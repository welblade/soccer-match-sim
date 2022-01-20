package com.github.welblade.soccermatchsim.domain.di

import com.github.welblade.soccermatchsim.domain.GetMatchesUseCase
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModules {
    fun load() {
        loadKoinModules(domainModule())
    }

    private fun domainModule(): Module {
        return module {
            factory { GetMatchesUseCase(get()) }
        }
    }
}
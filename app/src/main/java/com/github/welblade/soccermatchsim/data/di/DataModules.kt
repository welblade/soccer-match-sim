package com.github.welblade.soccermatchsim.data.di

import com.github.welblade.soccermatchsim.data.api.MatchesApi
import com.github.welblade.soccermatchsim.data.repository.MatchRepository
import com.github.welblade.soccermatchsim.data.repository.MatchRepositoryImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber

object DataModules {
    fun load() {
        loadKoinModules(networkModule() + repositoryModule())
    }

    private fun repositoryModule() = module {
        single<MatchRepository> { MatchRepositoryImpl(get()) }
    }

    private fun networkModule(): Module {
        return module {
            single {
                val interceptor = HttpLoggingInterceptor {
                    Timber.tag("OkHttp").d(it)
                }
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            }
            single {
                val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
                MoshiConverterFactory.create(moshi)
            }
            single {
                createService<MatchesApi>(get(), get())
            }
        }
    }

    private inline fun <reified T> createService(
        client: OkHttpClient,
        factory: MoshiConverterFactory
    ): T {
        return Retrofit.Builder()
            .baseUrl("https://welblade.github.io/soccer-match-fake-api/")
            .client(client)
            .addConverterFactory(factory)
            .build()
            .create(T::class.java)
    }
}
package com.github.welblade.soccermatchsim.data.di

import com.github.welblade.soccermatchsim.data.api.Matches
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
        loadKoinModules(networkModule())
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
                MoshiConverterFactory.create()
            }
            single {
                createService<Matches>(get(), get())
            }
        }
    }

    private inline fun <reified T> createService(
        client: OkHttpClient,
        factory: MoshiConverterFactory
    ): T {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(client)
            .addConverterFactory(factory)
            .build()
            .create(T::class.java)
    }
}
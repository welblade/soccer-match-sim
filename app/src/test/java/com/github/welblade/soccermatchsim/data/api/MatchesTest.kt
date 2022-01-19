package com.github.welblade.soccermatchsim.data.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.kotest.assertions.asClue
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import mockwebserver3.MockResponse
import mockwebserver3.MockWebServer
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class MatchesTest {

    private var server: MockWebServer = MockWebServer().also {
        it.enqueue(
            MockResponse()
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .addHeader("Cache-Control", "no-cache")
                .setBody(mockedResponse())
        )
        it.start()
    }

    @Test
    fun getMatches() {
        val baseUrl = server.url("/")
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val retro = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(Matches::class.java)
        runBlocking {
            launch {
                val matches = retro.getMatches()

                matches.asClue {
                    it.size shouldBeExactly 2
                }
                matches.first().asClue {
                    it.description shouldBe "Eliminatórias Copa 2022"
                    it.place shouldNotBe null
                    it.home shouldNotBe null
                    it.away shouldNotBe null
                }
            }
        }

    }

    @AfterAll
    fun closeServer() {
        server.shutdown()
    }

    private fun mockedResponse(): String {
        return """
            [
              {
                "descricao": "Eliminatórias Copa 2022",
                "local": {
                  "nome": "Maracanã",
                  "imagem": "https://www.bandeirasnacionais.com/data/flags/normal/br.png"
                },
                "mandante": {
                  "nome": "Brasil",
                  "forca": 5,
                  "imagem": "https://www.bandeirasnacionais.com/data/flags/normal/br.png"
                },
                "visitante": {
                  "nome": "Argentina",
                  "forca": 5,
                  "imagem": "https://www.bandeirasnacionais.com/data/flags/normal/ar.png"
                }
              },
              {
                "descricao": "Eliminatórias Copa 2022",
                "local": {
                  "nome": "Maracanã",
                  "imagem": "https://www.bandeirasnacionais.com/data/flags/normal/br.png"
                },
                "mandante": {
                  "nome": "Brasil",
                  "forca": 5,
                  "imagem": "https://www.bandeirasnacionais.com/data/flags/normal/br.png"
                },
                "visitante": {
                  "nome": "Argentina",
                  "forca": 5,
                  "imagem": "https://www.bandeirasnacionais.com/data/flags/normal/ar.png"
                }
              }
            ]
        """.trimIndent()
    }
}
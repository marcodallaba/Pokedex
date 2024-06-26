package it.marcodallaba.network.service

import com.google.gson.Gson
import it.marcodallaba.network.dto.PokemonInfoResponse
import it.marcodallaba.network.dto.PokemonListResponse
import it.marcodallaba.network.json.page1Json
import it.marcodallaba.network.json.pikachuJson
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class PokedexClientTest {

    @Mock
    private lateinit var mockPokedexApi: PokeApi

    private lateinit var pokedexClient: PokedexClient

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        pokedexClient = PokedexClient(mockPokedexApi)
    }

    @Test
    fun testFetchPokemonList() = runTest {
        val page = 1
        val pageSize = 20
        val gson = Gson()
        val expectedResponse = gson.fromJson(page1Json, PokemonListResponse::class.java)

        `when`(mockPokedexApi.getPokemonList(limit = pageSize, offset = page * pageSize))
            .thenReturn(expectedResponse)

        val actualResponse = pokedexClient.fetchPokemonList(page, pageSize)

        assertEquals(expectedResponse, actualResponse)
    }

    @Test
    fun testFetchPokemonInfo() = runTest {
        val pokemonName = "pikachu"
        val gson = Gson()
        val expectedResponse = gson.fromJson(pikachuJson, PokemonInfoResponse::class.java)

        `when`(mockPokedexApi.getPokemonInfo(name = pokemonName))
            .thenReturn(expectedResponse)

        val actualResponse = pokedexClient.fetchPokemonInfo(pokemonName)

        assertEquals(expectedResponse, actualResponse)
    }
}

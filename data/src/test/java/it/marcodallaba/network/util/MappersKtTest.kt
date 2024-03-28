package it.marcodallaba.network.util

import it.marcodallaba.network.dto.PokemonListResponse
import it.marcodallaba.network.dto.Result
import org.junit.Assert.assertEquals

import org.junit.Test

class MappersKtTest {
    @Test
    fun `test PokemonListResponse toModel`() {
        // Create a sample PokemonListResponse
        val response = PokemonListResponse(
            count = 2,
            next = null,
            previous = null,
            results = listOf(
                Result(
                    "bulbasaur",
                    "https://pokeapi.co/api/v2/pokemon/1/"
                ),
                Result("charmander", "https://pokeapi.co/api/v2/pokemon/4/")
            )
        )

        // Call the function to be tested
        val result = response.toModel()

        // Assert the result
        assertEquals(2, result.size)

        val bulbasaurImageUrl =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
        // Assert for PokemonListEntry 1
        assertEquals("bulbasaur", result[0].name)
        assertEquals(bulbasaurImageUrl, result[0].imageUrl)
        assertEquals("1", result[0].number)

        val charmanderImageUrl =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png"
        // Assert for PokemonListEntry 2
        assertEquals("charmander", result[1].name)
        assertEquals(charmanderImageUrl, result[1].imageUrl)
        assertEquals("4", result[1].number)
    }
}
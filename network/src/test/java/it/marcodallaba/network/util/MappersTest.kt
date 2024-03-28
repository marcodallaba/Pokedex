package it.marcodallaba.network.util

import com.google.gson.Gson
import it.marcodallaba.model.Stat
import it.marcodallaba.network.dto.PokemonInfoResponse
import it.marcodallaba.network.dto.PokemonListResponse
import it.marcodallaba.network.json.page1Json
import it.marcodallaba.network.json.pikachuJson
import org.junit.Assert.assertEquals

import org.junit.Test

class MappersKtTest {
    @Test
    fun `test PokemonListResponse toModel`() {
        // Create a sample PokemonListResponse
        val gson = Gson()
        val response = gson.fromJson(page1Json, PokemonListResponse::class.java)

        // Call the function to be tested
        val result = response.toModel()

        // Assert the result
        assertEquals(20, result.size)

        val bulbasaurImageUrl =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
        // Assert for PokemonListEntry 1
        assertEquals("bulbasaur", result[0].name)
        assertEquals(bulbasaurImageUrl, result[0].imageUrl)
        assertEquals("1", result[0].number)

        val charmanderImageUrl =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png"
        // Assert for PokemonListEntry 2
        assertEquals("charmander", result[3].name)
        assertEquals(charmanderImageUrl, result[3].imageUrl)
        assertEquals("4", result[3].number)
    }

    @Test
    fun `test PokemonInfoResponse toModel`() {
        val gson = Gson()
        val response = gson.fromJson(pikachuJson, PokemonInfoResponse::class.java)

        // Call the function to be tested
        val result = response.toModel()

        val frontImageDefaultUrl =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png"

        assertEquals(result.id, 25)
        assertEquals(result.name, "pikachu")
        assertEquals(result.frontImageDefaultUrl, frontImageDefaultUrl)
        assertEquals(result.types, listOf("electric"))
        assertEquals(
            result.stats,
            listOf(
                Stat(
                    "hp",
                    35,
                ),
                Stat(
                    "attack",
                    55,
                ),
                Stat(
                    "defense",
                    40,
                ),
                Stat(
                    "special-attack",
                    50
                ),
                Stat(
                    "special-defense",
                    50
                ),
                Stat(
                    "speed",
                    90
                )
            )
        )
        assertEquals(result.height, 4)
        assertEquals(result.weight, 60)
    }
}
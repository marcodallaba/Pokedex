package it.marcodallaba.data.repository

import dagger.hilt.android.scopes.ActivityScoped
import it.marcodallaba.data.util.Resource
import it.marcodallaba.model.PokemonInfo
import it.marcodallaba.model.PokemonListEntry
import it.marcodallaba.network.service.PokedexClient
import it.marcodallaba.network.util.toModel

@ActivityScoped
class PokemonRepository(
    private val pokedexClient: PokedexClient
) {

    suspend fun getPokemonListEntry(
        limit: Int,
    ): Resource<List<PokemonListEntry>> {
        val response = try {
            pokedexClient.fetchPokemonList(limit)
        } catch (e: Exception) {
            return Resource.Error("An exception has occurred")
        }
        return Resource.Success(response.toModel())
    }


    suspend fun getPokemonInfo(
        pokemonName: String,
    ): Resource<PokemonInfo> {
        val response = try {
            pokedexClient.fetchPokemonInfo(pokemonName)
        } catch (e: Exception) {
            return Resource.Error("An exception has occurred")
        }
        return Resource.Success(response.toModel())
    }
}

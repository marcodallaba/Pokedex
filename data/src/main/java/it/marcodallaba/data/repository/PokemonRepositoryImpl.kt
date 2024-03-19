package it.marcodallaba.data.repository

import dagger.hilt.android.scopes.ActivityScoped
import it.marcodallaba.data.util.Resource
import it.marcodallaba.model.PokemonInfo
import it.marcodallaba.model.PokemonListEntry
import it.marcodallaba.network.service.PokedexClient
import it.marcodallaba.network.util.toModel

typealias LastPage = Boolean

@ActivityScoped
class PokemonRepositoryImpl(
    private val pokedexClient: PokedexClient
) : PokemonRepository {

    suspend fun getPokemonListEntry(
        page: Int,
    ): Resource<Pair<List<PokemonListEntry>, LastPage>> {
        val response = try {
            pokedexClient.fetchPokemonList(page)
        } catch (e: Exception) {
            return Resource.Error("An exception has occurred")
        }
        return Resource.Success(Pair(response.toModel(), response.next == null))
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

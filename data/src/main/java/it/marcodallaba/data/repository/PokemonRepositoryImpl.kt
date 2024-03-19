package it.marcodallaba.data.repository

import dagger.hilt.android.scopes.ActivityScoped
import it.marcodallaba.common.network.Dispatcher
import it.marcodallaba.common.network.PokedexDispatchers
import it.marcodallaba.data.util.Result
import it.marcodallaba.model.PokemonInfo
import it.marcodallaba.model.PokemonListEntry
import it.marcodallaba.network.service.PokedexClient
import it.marcodallaba.network.util.toModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

typealias LastPage = Boolean

@ActivityScoped
class PokemonRepositoryImpl(
    private val pokedexClient: PokedexClient,
    @Dispatcher(PokedexDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : PokemonRepository {

    override suspend fun getPokemonListEntry(
        page: Int,
    ): Result<Pair<List<PokemonListEntry>, LastPage>> {
        return withContext(ioDispatcher) {
            val response = try {
                pokedexClient.fetchPokemonList(page)
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }
            return@withContext Result.Success(Pair(response.toModel(), response.next == null))
        }
    }


    override suspend fun getPokemonInfo(
        pokemonName: String,
    ): Result<PokemonInfo> {
        return withContext(ioDispatcher) {
            val response = try {
                pokedexClient.fetchPokemonInfo(pokemonName)
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }
            return@withContext Result.Success(response.toModel())
        }
    }
}

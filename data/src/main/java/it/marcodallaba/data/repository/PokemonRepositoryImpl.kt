package it.marcodallaba.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import it.marcodallaba.common.network.Dispatcher
import it.marcodallaba.common.network.PokedexDispatchers
import it.marcodallaba.data.util.Result
import it.marcodallaba.model.PokemonInfo
import it.marcodallaba.network.service.PokedexClient
import it.marcodallaba.network.util.toModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class PokemonRepositoryImpl(
    private val pokedexClient: PokedexClient,
    @Dispatcher(PokedexDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : PokemonRepository {
    override fun getPokemonListEntry(
        page: Int
    ) = Pager(
        PagingConfig(PAGE_SIZE)
    ) {
        PokemonPagingSource(
            pokedexClient
        )
    }.flow

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

    companion object {
        const val PAGE_SIZE = 20
    }
}
package it.marcodallaba.data.repository

import androidx.paging.PagingData
import it.marcodallaba.data.util.Result
import it.marcodallaba.model.PokemonInfo
import it.marcodallaba.model.PokemonListEntry
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonListEntry(
        page: Int
    ): Flow<PagingData<PokemonListEntry>>

    suspend fun getPokemonInfo(
        pokemonName: String,
    ): Result<PokemonInfo>
}

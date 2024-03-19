package it.marcodallaba.data.repository

import it.marcodallaba.data.util.Result
import it.marcodallaba.model.PokemonInfo
import it.marcodallaba.model.PokemonListEntry

interface PokemonRepository {
    suspend fun getPokemonListEntry(
        page: Int,
    ): Result<Pair<List<PokemonListEntry>, LastPage>>

    suspend fun getPokemonInfo(
        pokemonName: String,
    ): Result<PokemonInfo>
}
package it.marcodallaba.data.repository

import it.marcodallaba.data.util.Resource
import it.marcodallaba.model.PokemonInfo
import it.marcodallaba.model.PokemonListEntry

interface PokemonRepository {
    suspend fun getPokemonListEntry(
        page: Int,
    ): Resource<Pair<List<PokemonListEntry>, LastPage>>

    suspend fun getPokemonInfo(
        pokemonName: String,
    ): Resource<PokemonInfo>
}
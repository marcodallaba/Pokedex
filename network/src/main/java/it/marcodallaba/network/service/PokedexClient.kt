package it.marcodallaba.network.service

import it.marcodallaba.network.dto.PokemonInfoResponse
import it.marcodallaba.network.dto.PokemonListResponse
import javax.inject.Inject

class PokedexClient @Inject constructor(
  private val pokedexApi: PokeApi,
) {

  suspend fun fetchPokemonList(page: Int, pageSize: Int): PokemonListResponse =
    pokedexApi.getPokemonList(
      limit = pageSize,
      offset = page * pageSize,
    )

  suspend fun fetchPokemonInfo(name: String): PokemonInfoResponse =
    pokedexApi.getPokemonInfo(
      name = name,
    )
}

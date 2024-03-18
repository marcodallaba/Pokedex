package it.marcodallaba.network.service

import it.marcodallaba.network.dto.PokemonInfoResponse
import it.marcodallaba.network.dto.PokemonListResponse
import javax.inject.Inject

class PokedexClient @Inject constructor(
  private val pokedexApi: PokeApi,
) {

  suspend fun fetchPokemonList(page: Int): PokemonListResponse =
    pokedexApi.getPokemonList(
      limit = PAGING_SIZE,
      offset = page * PAGING_SIZE,
    )

  suspend fun fetchPokemonInfo(name: String): PokemonInfoResponse =
    pokedexApi.getPokemonInfo(
      name = name,
    )

  companion object {
    private const val PAGING_SIZE = 20
  }
}

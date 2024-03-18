package it.marcodallaba.network.service

import it.marcodallaba.network.dto.Pokemon
import it.marcodallaba.network.dto.PokemonList
import javax.inject.Inject

class PokedexClient @Inject constructor(
  private val pokedexApi: PokeApi,
) {

  suspend fun fetchPokemonList(page: Int): PokemonList =
    pokedexApi.getPokemonList(
      limit = PAGING_SIZE,
      offset = page * PAGING_SIZE,
    )

  suspend fun fetchPokemonInfo(name: String): Pokemon =
    pokedexApi.getPokemonInfo(
      name = name,
    )

  companion object {
    private const val PAGING_SIZE = 20
  }
}

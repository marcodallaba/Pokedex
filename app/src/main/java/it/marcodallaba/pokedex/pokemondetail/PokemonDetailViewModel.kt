package it.marcodallaba.pokedex.pokemondetail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import it.marcodallaba.data.repository.PokemonRepository
import it.marcodallaba.data.util.Result
import it.marcodallaba.model.PokemonInfo
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val repository: PokemonRepository,
) : ViewModel() {

    suspend fun getPokemonInfo(pokemonName: String): Result<PokemonInfo> {
        return repository.getPokemonInfo(pokemonName)
    }
}

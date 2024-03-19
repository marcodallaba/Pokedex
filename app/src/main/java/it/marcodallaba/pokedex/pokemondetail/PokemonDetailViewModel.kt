package it.marcodallaba.pokedex.pokemondetail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import it.marcodallaba.data.repository.PokemonRepositoryImpl
import it.marcodallaba.data.util.Resource
import it.marcodallaba.model.PokemonInfo
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val repository: PokemonRepositoryImpl,
) : ViewModel() {

    suspend fun getPokemonInfo(pokemonName: String): Resource<PokemonInfo> {
        return repository.getPokemonInfo(pokemonName)
    }
}
